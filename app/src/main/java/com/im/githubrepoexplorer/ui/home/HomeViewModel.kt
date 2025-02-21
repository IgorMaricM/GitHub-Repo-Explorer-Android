package com.im.githubrepoexplorer.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.im.githubrepoexplorer.database.RoomDB
import com.im.githubrepoexplorer.networking.ContributorModel
import com.im.githubrepoexplorer.networking.KtorClient
import com.im.githubrepoexplorer.networking.RepoModel
import com.im.githubrepoexplorer.networking.RepoPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val database: RoomDB, private val networking: KtorClient
) : ViewModel() {

    private var currentSearchJob: Job? = null

    val searchQuery = mutableStateOf("")
    val repoDetail = mutableStateOf<RepoModel?>(null)
    val repoContributors = mutableListOf<ContributorModel>()
    val loadingRepoDetails = mutableStateOf(false)

    private val _favoriteRepos = MutableStateFlow<List<RepoModel>>(emptyList())
    val favoriteRepos = _favoriteRepos.asStateFlow()

    private val _favoriteContributors = MutableStateFlow<List<ContributorModel>>(emptyList())
    val favoriteContributors = _favoriteContributors.asStateFlow()

    val repoPaging: MutableStateFlow<PagingData<RepoModel>> = MutableStateFlow(value = PagingData.empty())

    init {
        updateSearchQuery("")

        viewModelScope.launch(Dispatchers.IO) {
            _favoriteRepos.value = database.fetchRepos()
            _favoriteContributors.value = database.fetchContributors()
        }
    }

    fun updateSearchQuery(query: String) {
        searchQuery.value = query.take(255)

        currentSearchJob?.cancel()
        currentSearchJob = viewModelScope.launch(Dispatchers.IO) {
            delay(300)
            Pager(config = PagingConfig(
                pageSize = KtorClient.PAGINATION_SIZE, prefetchDistance = 1
            ), pagingSourceFactory = {
                RepoPagingSource(networking, searchQuery.value)
            }).flow.cachedIn(viewModelScope).collect {
                repoPaging.value = it
            }
        }
    }

    // Update isFavorite to return Boolean directly
    fun isFavorite(repoId: Int): Boolean {
        return favoriteRepos.value.any { it.id == repoId }
    }

    // Update toggleFavorite to handle both add and remove
    fun toggleFavorite(repo: RepoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingRepo = database.fetchRepo(repo.id)

            if (existingRepo != null) {
                Timber.d("Removing repo from favourites: ${repo.name}")
                database.deleteRepo(existingRepo)
                _favoriteRepos.update { current ->
                    current.filter { it.id != repo.id }
                }
            } else {
                Timber.d("Saving repo to favourites: ${repo.name}")
                database.insertRepo(repo)
                _favoriteRepos.update { current ->
                    current + repo
                }
            }
        }
    }

    fun isContributorFavorite(id: Int): Boolean {
        return favoriteContributors.value.any { it.id == id }
    }

    fun toggleFavoriteCollaborator(collaborator: ContributorModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingContributor = database.fetchContributor(collaborator.id)

            if (existingContributor != null) {
                Timber.d("Removing contributor from favourites: ${collaborator.login}")
                database.deleteContributor(collaborator)
                //TODO handle favourite toggle with removal with undo
                _favoriteContributors.update { current ->
                    current.filter { it.id != collaborator.id }
                }
            } else {
                Timber.d("Saving contributor to favourites: ${collaborator.login}")
                database.insertContributor(collaborator)
                _favoriteContributors.update { current ->
                    current + collaborator
                }
            }
        }
    }

    fun getRepoDetails(owner: String?) {
        if (owner.isNullOrEmpty()) {
            repoDetail.value = null
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            loadingRepoDetails.value = true
            //TODO improve error handling
            val rd = networking.getRepoDetails(owner)
            repoDetail.value = null
            repoContributors.clear()
            rd?.contributorsUrl?.let {
                //TODO handle contributor pagination
                repoContributors.addAll(networking.getContributors(it))
                repoDetail.value = rd
            }
            loadingRepoDetails.value = false
        }
    }
}
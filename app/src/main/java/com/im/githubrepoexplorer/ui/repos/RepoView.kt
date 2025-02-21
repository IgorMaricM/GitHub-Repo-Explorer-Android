package com.im.githubrepoexplorer.ui.repos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.im.githubrepoexplorer.networking.RepoModel
import com.im.githubrepoexplorer.ui.home.HomeViewModel
import timber.log.Timber

@Composable
fun ReposHome(viewModel: HomeViewModel, onRepoDetailClick: (RepoModel) -> Unit) {

    val repoItems: LazyPagingItems<RepoModel> = viewModel.repoPaging.collectAsLazyPagingItems()
    val searchQuery = remember { viewModel.searchQuery }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(query = searchQuery.value, onQueryChange = viewModel::updateSearchQuery)

        LazyColumn(
            modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(repoItems.itemCount) { index ->
                val repo = repoItems[index] ?: return@items

                RepoItem(repo = repo,
                    isFavorite = viewModel.isFavorite(repo.id),
                    onFavoriteClick = { viewModel.toggleFavorite(repo) },
                    onClick = {
                        onRepoDetailClick.invoke(repo)
                    })
            }

            // Handle different loading states
            when (repoItems.loadState.append) {
                is LoadState.Loading -> {
                    item {
                        if (repoItems.loadState.append == LoadState.Loading) {
                            LoadingItemView()
                        }
                    }
                }

                is LoadState.Error -> {
                    item {
                        val error = (repoItems.loadState.append as LoadState.Error).error
                        ErrorItemView(error)
                    }
                }

                else -> {}
            }

            // Handle initial load state
            when (repoItems.loadState.refresh) {
                is LoadState.Loading -> {
                    item {
                        LoadingItemView()
                    }
                }

                is LoadState.Error -> {
                    item {
                        val error = (repoItems.loadState.refresh as LoadState.Error).error
                        ErrorItemView(error)
                    }
                }

                else -> {}
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String, onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = { Text("Search repositories") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable {
                    onQueryChange.invoke("")
                }, imageVector = Icons.Default.Clear, contentDescription = "Clear"
            )
        },
        singleLine = true
    )
}

@Composable
fun LoadingItemView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(40.dp))
    }
}

@Composable
fun ErrorItemView(error: Throwable) {
    Text(
        text = "Error: ${error.localizedMessage}",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.error
    )
}

@Composable
fun RepoItem(
    repo: RepoModel, isFavorite: Boolean, onFavoriteClick: () -> Unit, onClick: () -> Unit
) {
    //field to remember state and update local before changing state in db
    val isFav = remember { mutableStateOf(isFavorite) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                Timber.d("Repo item clicked")
                onClick.invoke()
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AsyncImage(
                        model = repo.owner.avatarUrl, contentDescription = null, modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = repo.owner.login, style = MaterialTheme.typography.titleMedium
                    )
                }

                IconButton(onClick = {
                    isFav.value = !isFav.value
                    onFavoriteClick.invoke()
                }) {
                    Icon(
                        imageVector = if (isFav.value) {
                            Icons.Filled.Favorite
                        } else {
                            Icons.Outlined.FavoriteBorder
                        }, contentDescription = if (isFav.value) {
                            "Remove from favorites"
                        } else {
                            "Add to favorites"
                        }, tint = if (isFav.value) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = repo.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            if (repo.description.isNotEmpty()) {
                Text(
                    text = repo.description, style = MaterialTheme.typography.bodyMedium, maxLines = 2, overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                RepoStat(icon = Icons.Default.Language, text = repo.language)
                RepoStat(icon = Icons.Default.Star, count = repo.stargazersCount)
                RepoStat(icon = Icons.Default.AccountTree, count = repo.forksCount)
                RepoStat(icon = Icons.Default.BugReport, count = repo.openIssues)
                RepoStat(icon = Icons.Default.Visibility, count = repo.watchersCount)
            }
        }
    }
}

@Composable
fun RepoStat(
    icon: ImageVector, text: String? = null, count: Int? = null
) {
    Row(
        modifier = Modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = icon, contentDescription = null, modifier = Modifier.size(16.dp)
        )
        Text(
            text = text ?: count?.toString() ?: "", style = MaterialTheme.typography.bodySmall
        )
    }
}
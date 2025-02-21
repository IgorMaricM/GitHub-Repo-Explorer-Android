package com.im.githubrepoexplorer.ui.favourites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.im.githubrepoexplorer.networking.ContributorModel
import com.im.githubrepoexplorer.ui.home.HomeViewModel
import com.im.githubrepoexplorer.ui.repos.RepoItem

@Composable
fun FavouritesHome(viewModel: HomeViewModel) {
    var selectedTabIndex = remember { mutableIntStateOf(0) }
    val tabs = listOf("Repositories", "Contributors")

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex.intValue, modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(selected = selectedTabIndex.intValue == index, onClick = { selectedTabIndex.intValue = index }, text = { Text(title) })
            }
        }

        when (selectedTabIndex.intValue) {
            0 -> FavoriteReposList(viewModel)
            1 -> FavoriteContributorsList(viewModel)
        }
    }
}

@Composable
private fun FavoriteReposList(viewModel: HomeViewModel) {
    val favorites by viewModel.favoriteRepos.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(favorites) { repo ->
            RepoItem(repo = repo,
                isFavorite = true,
                onFavoriteClick = { viewModel.toggleFavorite(repo) },
                onClick = {}
            )
        }
    }
}

@Composable
private fun FavoriteContributorsList(viewModel: HomeViewModel) {
    val favorites by viewModel.favoriteContributors.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(favorites) { collaborator ->
            CollaboratorItem(collaborator = collaborator,
                isFavorite = true,
                onFavoriteClick = { viewModel.toggleFavoriteCollaborator(collaborator) })
        }
    }
}

@Composable
private fun CollaboratorItem(
    collaborator: ContributorModel, isFavorite: Boolean, onFavoriteClick: () -> Unit
) {
    val isFav = remember { mutableStateOf(isFavorite) }
    Card(
        modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = collaborator.avatarUrl, contentDescription = null, modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Text(text = collaborator.login ?: "")
            }
            IconButton(onClick = {
                isFav.value = !isFav.value
                onFavoriteClick.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Remove from favorites",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
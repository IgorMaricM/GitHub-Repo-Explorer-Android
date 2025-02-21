package com.im.githubrepoexplorer.ui.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.im.githubrepoexplorer.R
import com.im.githubrepoexplorer.networking.ContributorModel
import com.im.githubrepoexplorer.ui.home.HomeViewModel
import com.im.githubrepoexplorer.ui.repos.RepoStat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun RepoDetailView(viewModel: HomeViewModel) {
    val repoDetail = remember { viewModel.repoDetail }
    val isLoading = remember { viewModel.loadingRepoDetails }
    val context = LocalContext.current

    if (isLoading.value == true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 100.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = stringResource(R.string.loading_repository_data)
            )
            CircularProgressIndicator(modifier = Modifier.size(40.dp))
        }

        return
    }

    if (repoDetail.value == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(stringResource(R.string.repository_data_is_missing))
        }

        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val repo = repoDetail.value!!
        val isFav = remember { mutableStateOf(viewModel.isFavorite(repo.id)) }

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
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

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = repo.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    IconButton(onClick = {
                        isFav.value = !isFav.value
                        viewModel.toggleFavorite(repo)
                    }) {
                        Icon(
                            imageVector = if (isFav.value) {
                                Icons.Filled.Favorite
                            } else {
                                Icons.Outlined.FavoriteBorder
                            }, contentDescription = if (isFav.value) {
                                stringResource(R.string.remove_from_favorites)
                            } else {
                                stringResource(R.string.add_to_favorites)
                            }, tint = if (isFav.value) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurface
                            }
                        )
                    }
                }

                Text(
                    text = repo.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                // Stats Row
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    RepoStat(icon = Icons.Default.Language, text = repo.language)
                    RepoStat(icon = Icons.Default.Star, count = repo.stargazersCount)
                    RepoStat(icon = Icons.Default.AccountTree, count = repo.forksCount)
                    RepoStat(icon = Icons.Default.BugReport, count = repo.openIssues)
                    RepoStat(icon = Icons.Default.Visibility, count = repo.watchersCount)
                }

                Spacer(Modifier.size(10.dp))

                // Additional Info
                InfoRow(stringResource(R.string.default_branch), repo.defaultBranch)
                InfoRow(stringResource(R.string.created), repo.createdAt.formatDate())
                InfoRow(stringResource(R.string.updated), repo.updatedAt.formatDate())

                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repo.htmlUrl))
                        context.startActivity(intent)
                    }, modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.open_in_browser))
                }
            }
        }

        // Contributors List
        Text(
            text = stringResource(R.string.contributors),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        LazyColumn {
            items(viewModel.repoContributors) { contributor ->
                ContributorItem(contributor = contributor,
                    isFavorite = viewModel.isContributorFavorite(contributor.id),
                    onFavoriteClick = {
                        viewModel.toggleFavoriteCollaborator(contributor)
                    })
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun ContributorItem(
    contributor: ContributorModel, isFavorite: Boolean, onFavoriteClick: () -> Unit
) {
    val isFav = remember { mutableStateOf(isFavorite) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp), elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = contributor.avatarUrl, contentDescription = null, modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                )
                Text(text = contributor.login ?: "")
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
                    }, contentDescription = stringResource(R.string.toggle_favorite), tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

private fun String.formatDate(): String {
    return try {
        val instant = Instant.parse(this)
        DateTimeFormatter.ofPattern("MMM dd, yyyy").withZone(ZoneId.systemDefault()).format(instant)
    } catch (e: Exception) {
        this
    }
}
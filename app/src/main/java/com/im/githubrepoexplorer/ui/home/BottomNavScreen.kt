package com.im.githubrepoexplorer.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector


enum class NavScreens {
    Repos, Favourites
}

sealed class BottomNavScreen(
    val route: String,
    val name: String,
    val icon: ImageVector
) {
    data object Repos : BottomNavScreen(NavScreens.Repos.name, "Repos", Icons.Filled.List)
    data object Favourites : BottomNavScreen(NavScreens.Favourites.name, "Favourites", Icons.Filled.Favorite)
}

val bottomNavScreenList = listOf(
    BottomNavScreen.Repos,
    BottomNavScreen.Favourites,
)
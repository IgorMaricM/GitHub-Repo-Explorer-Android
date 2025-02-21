package com.im.githubrepoexplorer.ui.home

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import timber.log.Timber

@Composable
fun HomeBottomView(
    repoHome: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
    favouriteHome: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val selectedIndex = remember { mutableIntStateOf(0) }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            tonalElevation = 3.dp
        ) {
            bottomNavScreenList.forEachIndexed { index, screen ->
                val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                NavigationBarItem(icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.name,
                        tint = if (selected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                }, label = {
                    Text(
                        text = screen.name,
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Visible,
                        softWrap = false,
                    )
                }, selected = selected,
                    onClick = {
                        selectedIndex.intValue = index
                        navController.navigate(screen.route) {
                            // Pop up to the start destination of the graph to avoid building up
                            // a large stack of destinations on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when re-selecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }

                    })
            }
        }
    }) { innerPadding ->
        Timber.d("Home innerPadding: $innerPadding")
        NavHost(
            navController = navController,
            startDestination = NavScreens.Repos.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(
                route = NavScreens.Repos.name,
                content = repoHome
            )
            composable(
                route = NavScreens.Favourites.name,
                content = favouriteHome
            )
        }
    }
}




package com.im.githubrepoexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.im.githubrepoexplorer.ui.compose.AppTheme
import com.im.githubrepoexplorer.ui.details.DetailsActivity
import com.im.githubrepoexplorer.ui.favourites.FavouritesHome
import com.im.githubrepoexplorer.ui.home.HomeBottomView
import com.im.githubrepoexplorer.ui.home.HomeViewModel
import com.im.githubrepoexplorer.ui.repos.ReposHome
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                MainView()
            }
        }
    }

    @Composable
    fun MainView() {
        HomeBottomView(repoHome = {
            ReposHome(viewModel) { repo ->
                DetailsActivity.start(this@MainActivity, repo.fullName)
            }
        }, favouriteHome = {
            FavouritesHome(viewModel)
        })
    }
}
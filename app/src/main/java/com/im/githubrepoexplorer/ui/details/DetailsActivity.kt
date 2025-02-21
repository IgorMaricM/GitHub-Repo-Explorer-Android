package com.im.githubrepoexplorer.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.im.githubrepoexplorer.ui.compose.AppTheme
import com.im.githubrepoexplorer.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailsActivity : ComponentActivity() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Scaffold(Modifier.fillMaxSize()) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        RepoDetailView(viewModel)
                    }
                }
            }
        }

        val owner = intent.getStringExtra(INTENT_EXTRA_OWNER)
        viewModel.getRepoDetails(owner)
    }

    companion object {
        private const val INTENT_EXTRA_OWNER = "intent_owner"

        fun start(context: Context, owner: String) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(INTENT_EXTRA_OWNER, owner)
            context.startActivity(intent)
        }
    }
}
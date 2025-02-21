package com.im.githubrepoexplorer.networking

import androidx.paging.PagingSource
import androidx.paging.PagingState


class RepoPagingSource(private val networkClient: KtorClient, private val search: String) : PagingSource<Int, RepoModel>() {

    override fun getRefreshKey(state: PagingState<Int, RepoModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoModel> {
        val currentPage = params.key ?: 1
        val response =
            networkClient.getAllRepos(search, currentPage, KtorClient.PAGINATION_SIZE) ?: return LoadResult.Error(Exception("Response is null"))
        val lastPossiblePage = (response.totalCount + KtorClient.PAGINATION_SIZE - 1) / KtorClient.PAGINATION_SIZE

        return LoadResult.Page(
            data = response.items,
            prevKey = if (currentPage == 1) null else currentPage - 1,
            nextKey = if (currentPage >= lastPossiblePage || response.items.isEmpty()) null else currentPage + 1
        )
    }
}
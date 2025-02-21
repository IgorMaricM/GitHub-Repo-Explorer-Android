package com.im.githubrepoexplorer.database

import android.content.Context
import androidx.room.Room
import com.im.githubrepoexplorer.networking.ContributorModel
import com.im.githubrepoexplorer.networking.RepoModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ReposDb {
        return Room.databaseBuilder(
            context,
            ReposDb::class.java,
            REPOS_DATABASE_NAME
        )
            .addTypeConverter(OwnerDbConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRoomDB(database: ReposDb): RoomDB {
        return RoomDB(database)
    }

    const val REPOS_DATABASE_NAME = "gh_repos"
    const val CONTRIBUTOR_DATABASE_NAME = "gh_contributor"
}

class RoomDB @Inject constructor(
    private val repoDb: ReposDb,
) {
    suspend fun insertRepo(value: RepoModel) {
        repoDb.reposDao.insert(value)
    }

    suspend fun fetchRepo(id: Int): RepoModel? {
        return repoDb.reposDao.fetch(id)
    }

    suspend fun fetchRepos(): List<RepoModel> {
        return repoDb.reposDao.fetch()
    }

    suspend fun deleteRepo(value: RepoModel) {
        repoDb.reposDao.delete(value)
    }

    // New contributor methods
    suspend fun insertContributor(contributor: ContributorModel) {
        repoDb.contributorDao.insert(contributor)
    }

    suspend fun deleteContributor(contributor: ContributorModel) {
        repoDb.contributorDao.delete(contributor)
    }

    suspend fun fetchContributor(id: Int): ContributorModel? {
        return repoDb.contributorDao.fetch(id)
    }

    suspend fun fetchContributors(): List<ContributorModel> {
        return repoDb.contributorDao.fetch()
    }

    suspend fun clearDatabase() {
        repoDb.contributorDao.deleteAll()
        repoDb.reposDao.deleteAll()
    }
}



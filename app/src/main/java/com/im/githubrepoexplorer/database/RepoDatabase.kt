package com.im.githubrepoexplorer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.im.githubrepoexplorer.networking.ContributorModel
import com.im.githubrepoexplorer.networking.RepoModel

@Database(entities = [RepoModel::class, ContributorModel::class], version = 1, exportSchema = false)
@TypeConverters(OwnerDbConverter::class)
abstract class ReposDb : RoomDatabase() {
    abstract val reposDao: ReposDao
    abstract val contributorDao: ContributorDao
}
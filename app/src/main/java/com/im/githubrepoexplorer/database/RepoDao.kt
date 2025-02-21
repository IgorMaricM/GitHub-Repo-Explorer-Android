package com.im.githubrepoexplorer.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.im.githubrepoexplorer.networking.RepoModel
import java.util.UUID


@Dao
interface ReposDao {
    @Query("select * from gh_repos")
    suspend fun fetch(): List<RepoModel>

    @Query("select * from gh_repos where id = :id limit 1")
    suspend fun fetch(id: Int): RepoModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: RepoModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: List<RepoModel>)

    @Update(entity = RepoModel::class)
    suspend fun update(data: RepoModel)

    @Delete
    suspend fun delete(data: RepoModel)

    @Query("DELETE FROM gh_repos")
    suspend fun deleteAll()
}
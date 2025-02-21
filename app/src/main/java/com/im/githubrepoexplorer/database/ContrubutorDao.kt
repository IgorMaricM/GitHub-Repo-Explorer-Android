package com.im.githubrepoexplorer.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.im.githubrepoexplorer.networking.ContributorModel

@Dao
interface ContributorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contributor: ContributorModel)

    @Delete
    suspend fun delete(contributor: ContributorModel)

    @Query("SELECT * FROM ${DatabaseModule.CONTRIBUTOR_DATABASE_NAME}")
    suspend fun fetch(): List<ContributorModel>

    @Query("SELECT * FROM ${DatabaseModule.CONTRIBUTOR_DATABASE_NAME} WHERE id = :id")
    suspend fun fetch(id: Int): ContributorModel?

    @Query("DELETE FROM ${DatabaseModule.CONTRIBUTOR_DATABASE_NAME}")
    suspend fun deleteAll()
}
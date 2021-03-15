package com.android.hogwartsenciclopedia.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query("SELECT * FROM characterentity")
    fun getAll(): LiveData<List<CharacterEntity>>

    @Query("SELECT * FROM characterentity WHERE name = :id LIMIT 1")
    suspend fun getById(id: String): CharacterEntity
}
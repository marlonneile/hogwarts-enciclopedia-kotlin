package com.android.hogwartsenciclopedia.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import javax.inject.Inject

class CharactersLocalDataSource @Inject constructor(private val applicationContext: Context) : LocalDataSource<DatabaseModel> {
    private val database = Room.databaseBuilder(
            applicationContext,
            CharactersDatabase::class.java,
            "characters"
        ).addMigrations(MIGRATION_1_2)
        .build()

    override suspend fun insertAll(items: Collection<DatabaseModel>) {
        @Suppress("UNCHECKED_CAST")
        database.charactersDao.insertAll(items as List<CharacterEntity>)
    }

    override fun getAll(): LiveData<List<CharacterEntity>> {
        return database.charactersDao.getAll()
    }

    override suspend fun getById(id: Any): CharacterEntity {
        return database.charactersDao.getById(id as String)
    }
}
package com.android.hogwartsenciclopedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.android.hogwartsenciclopedia.database.DatabaseModel
import com.android.hogwartsenciclopedia.database.LocalDataSource
import com.android.hogwartsenciclopedia.domain.DomainModel
import com.android.hogwartsenciclopedia.network.RemoteDataSource
import javax.inject.Inject

class CharactersRepository @Inject constructor(private val localDataSource: LocalDataSource<DatabaseModel>, private val remoteDataSource: RemoteDataSource) {
    fun getAll(): LiveData<List<DomainModel>> = Transformations.map(localDataSource.getAll()) {
        it.asDomainModel()
    }

    suspend fun refresh() {
        val characters = remoteDataSource.fetchAll()
        localDataSource.insertAll(characters.asDatabaseModel())
    }

    suspend fun getById(id: String): DomainModel {
        return localDataSource.getById(id).asDomainModel(DataTransferObjects.FULL)
    }
}
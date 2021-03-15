package com.android.hogwartsenciclopedia.repository

import com.android.hogwartsenciclopedia.database.DatabaseModel
import com.android.hogwartsenciclopedia.database.LocalDataSource
import com.android.hogwartsenciclopedia.domain.DomainModel
import com.android.hogwartsenciclopedia.network.RemoteDataSource

interface Repository<T : DatabaseModel, K: DomainModel> {
    val localDataSource: LocalDataSource<T>
    val remoteDataSource: RemoteDataSource

    suspend fun getAll(): Collection<K>
    suspend fun refresh()
}
package com.android.hogwartsenciclopedia.network

interface RemoteDataSource {
    suspend fun fetchAll(): Collection<NetworkModel>
}
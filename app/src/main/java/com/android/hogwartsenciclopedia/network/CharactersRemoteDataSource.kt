package com.android.hogwartsenciclopedia.network

import javax.inject.Inject

class CharactersRemoteDataSource @Inject constructor(charactersService: HarryPotterApiService) : RemoteDataSource {
    private val service = charactersService

    override suspend fun fetchAll(): List<CharacterNetworkModel> = service.fetchAllCharacters()
}
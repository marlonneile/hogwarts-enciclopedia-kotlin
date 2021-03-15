package com.android.hogwartsenciclopedia.network

import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface HarryPotterApiService {
    @GET("characters")
    suspend fun fetchAllCharacters(): List<CharacterNetworkModel>

    @GET("characters/students")
    suspend fun fetchStudents(): List<CharacterNetworkModel>

    @GET("characters/staff")
    suspend fun fetchStaff(): List<CharacterNetworkModel>

    @GET("characters/house/{house}")
    suspend fun fetchCharactersFrom(@Path("house") house: String): List<CharacterNetworkModel>
}
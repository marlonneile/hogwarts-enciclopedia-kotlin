package com.android.hogwartsenciclopedia.di

import android.content.Context
import com.android.hogwartsenciclopedia.database.CharactersLocalDataSource
import com.android.hogwartsenciclopedia.network.CharactersRemoteDataSource
import com.android.hogwartsenciclopedia.network.HarryPotterApiService
import com.android.hogwartsenciclopedia.network.HarryPotterNetwork
import com.android.hogwartsenciclopedia.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Provides
    fun provideApiService(): HarryPotterApiService = HarryPotterNetwork.apiService

    @Singleton
    @Provides
    fun provideRemoteDataSource(charactersService: HarryPotterApiService) = CharactersRemoteDataSource(charactersService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext applicationContext: Context) = CharactersLocalDataSource(applicationContext)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: CharactersRemoteDataSource,
                          localDataSource: CharactersLocalDataSource) = CharactersRepository(localDataSource, remoteDataSource)
}
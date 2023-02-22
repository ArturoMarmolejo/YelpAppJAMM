package com.arturomarmolejo.yelpappjamm.di

import com.arturomarmolejo.yelpappjamm.database.LocalRepository
import com.arturomarmolejo.yelpappjamm.database.LocalRepositoryImpl
import com.arturomarmolejo.yelpappjamm.service.YelpRepository
import com.arturomarmolejo.yelpappjamm.service.YelpRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRestaurantsRepo(
        yelpRepositoryImpl: YelpRepositoryImpl
    ): YelpRepository

    @Binds
    abstract fun provideLocalRepo(
        localRepositoryImpl: LocalRepositoryImpl
    ): LocalRepository
}
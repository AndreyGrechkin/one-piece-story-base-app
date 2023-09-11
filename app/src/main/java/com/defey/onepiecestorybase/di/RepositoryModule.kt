package com.defey.onepiecestorybase.di

import com.defey.onepiecestorybase.data.local.localDataSource.IslandLocalDS
import com.defey.onepiecestorybase.data.local.localDataSource.IslandLocalDSImpl
import com.defey.onepiecestorybase.data.remote.remoteDataSource.IslandRemoteDS
import com.defey.onepiecestorybase.data.remote.remoteDataSource.IslandRemoteDSImpl
import com.defey.onepiecestorybase.data.repository.IslandRepositoryImpl
import com.defey.onepiecestorybase.data.repository.PlaceRepositoryImpl
import com.defey.onepiecestorybase.domain.repository.IslandRepository
import com.defey.onepiecestorybase.domain.repository.PlaceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun providePlaceRepository(impl: PlaceRepositoryImpl): PlaceRepository

    @Binds
    @Singleton
    abstract fun provideIslandRemoteDS(impl: IslandRemoteDSImpl): IslandRemoteDS

    @Binds
    @Singleton
    abstract fun provideIslandLocalDS(impl: IslandLocalDSImpl): IslandLocalDS

    @Binds
    @Singleton
    abstract fun provideIslandRepository(impl: IslandRepositoryImpl): IslandRepository
}
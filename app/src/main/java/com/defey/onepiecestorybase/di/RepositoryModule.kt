package com.defey.onepiecestorybase.di

import android.content.Context
import com.defey.onepiecestorybase.data.repository.MapsRepositoryImpl
import com.defey.onepiecestorybase.data.repository.PlaceRepositoryImpl
import com.defey.onepiecestorybase.domain.repository.MapsRepository
import com.defey.onepiecestorybase.domain.repository.PlaceRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun providePlaceRepository(impl: PlaceRepositoryImpl): PlaceRepository


}
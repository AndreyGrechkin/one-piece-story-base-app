package com.defey.onepiecestorybase.di

import android.content.Context
import com.defey.onepiecestorybase.data.local.sharedPref.DataStorePreferencesImpl
import com.defey.onepiecestorybase.data.repository.MapsRepositoryImpl
import com.defey.onepiecestorybase.domain.repository.DataStorePreferences
import com.defey.onepiecestorybase.domain.repository.MapsRepository
import com.defey.onepiecestorybase.presentation.utils.Constants.DATA_STORE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStorePreferences(@ApplicationContext context: Context): DataStorePreferences =
        DataStorePreferencesImpl(context = context, name = DATA_STORE_NAME)

    @Provides
    @Singleton
    fun provideMapsRepository(@ApplicationContext context: Context): MapsRepository =
        MapsRepositoryImpl(context = context)
}
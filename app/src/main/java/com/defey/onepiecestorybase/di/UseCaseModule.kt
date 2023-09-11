package com.defey.onepiecestorybase.di

import com.defey.onepiecestorybase.domain.repository.DataStorePreferences
import com.defey.onepiecestorybase.domain.repository.IslandRepository
import com.defey.onepiecestorybase.domain.repository.PlaceRepository
import com.defey.onepiecestorybase.domain.usecase.place.DeleteOldAvatarPlaceUseCase
import com.defey.onepiecestorybase.domain.usecase.place.GetIslandsUseCase
import com.defey.onepiecestorybase.domain.usecase.place.GetLastPlaceUseCase
import com.defey.onepiecestorybase.domain.usecase.place.GetAvatarPlaceUseCase
import com.defey.onepiecestorybase.domain.usecase.place.GetNextTimeUseCase
import com.defey.onepiecestorybase.domain.usecase.place.SyncMapUseCase
import com.defey.onepiecestorybase.domain.usecase.place.SynchronizeIslandUseCase
import com.defey.onepiecestorybase.domain.usecase.place.SynchronizePersonageIslandUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    fun provideSynchronizePersonageIslandUseCase(
        repo: IslandRepository
    ): SynchronizePersonageIslandUseCase = SynchronizePersonageIslandUseCase(repo)

    @Provides
    fun provideGetAvatarPlaceUseCase(
        repo: IslandRepository
    ): GetAvatarPlaceUseCase = GetAvatarPlaceUseCase(repo)

    @Provides
    fun provideSynchronizeIslandUseCase(
        repo: IslandRepository
    ): SynchronizeIslandUseCase = SynchronizeIslandUseCase(repo)

    @Provides
    fun provideDeleteOldAvatarPlaceUseCase(
        repo: IslandRepository
    ): DeleteOldAvatarPlaceUseCase = DeleteOldAvatarPlaceUseCase(repo)

    @Provides
    fun provideGetIslandsUseCase(
        repo: IslandRepository,
        placeRepo: PlaceRepository
    ): GetIslandsUseCase = GetIslandsUseCase(repo, placeRepo)

    @Provides
    fun provideSyncMapUseCase(
        repo: PlaceRepository
    ): SyncMapUseCase = SyncMapUseCase(repo)

    @Provides
    fun provideGetLastPlaceUseCase(
        repo: PlaceRepository
    ): GetLastPlaceUseCase = GetLastPlaceUseCase(repo)

    @Provides
    fun provideGetNextTimeUseCase(
        repo: DataStorePreferences
    ): GetNextTimeUseCase = GetNextTimeUseCase(repo)
}
package com.defey.onepiecestorybase.di

import com.defey.onepiecestorybase.domain.repository.BandPersonageRepository
import com.defey.onepiecestorybase.domain.repository.BandRepository
import com.defey.onepiecestorybase.domain.repository.DataStorePreferences
import com.defey.onepiecestorybase.domain.repository.FruitRepository
import com.defey.onepiecestorybase.domain.repository.IslandRepository
import com.defey.onepiecestorybase.domain.repository.MangaRepository
import com.defey.onepiecestorybase.domain.repository.PersonageDescriptionRepository
import com.defey.onepiecestorybase.domain.repository.PersonageRepository
import com.defey.onepiecestorybase.domain.repository.PlaceDescriptionRepository
import com.defey.onepiecestorybase.domain.repository.PlaceRepository
import com.defey.onepiecestorybase.domain.repository.RewardRepository
import com.defey.onepiecestorybase.domain.repository.SkillRepository
import com.defey.onepiecestorybase.domain.repository.SubjectRepository
import com.defey.onepiecestorybase.domain.repository.WeaponsRepository
import com.defey.onepiecestorybase.domain.usecase.lists.GetBandListUseCase
import com.defey.onepiecestorybase.domain.usecase.lists.GetFruitListUseCase
import com.defey.onepiecestorybase.domain.usecase.lists.GetLocationListUseCase
import com.defey.onepiecestorybase.domain.usecase.lists.GetPersonageListUseCase
import com.defey.onepiecestorybase.domain.usecase.lists.GetSubjectListUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetCareerUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetFruitUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetMangaUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetPersonageDescriptionUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetPersonageUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetRewardUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetSkillUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetWeaponsUseCase
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

    @Provides
    fun provideGetPersonageListUseCase(
        personageRepo: PersonageRepository,
        personageDescriptionRepo: PersonageDescriptionRepository,
        bandRepo: BandRepository,
        bandPersonageRepo: BandPersonageRepository
    ): GetPersonageListUseCase =
        GetPersonageListUseCase(personageRepo, personageDescriptionRepo, bandRepo, bandPersonageRepo)

    @Provides
    fun provideGetBandListUseCase(
        bandRepo: BandRepository
    ): GetBandListUseCase = GetBandListUseCase(bandRepo)

    @Provides
    fun provideGetLocationListUseCase(
        placeRepo: PlaceRepository,
        descriptionRepo: PlaceDescriptionRepository
    ): GetLocationListUseCase = GetLocationListUseCase(placeRepo, descriptionRepo)

    @Provides
    fun provideGetSubjectListUseCase(
        repo: SubjectRepository
    ): GetSubjectListUseCase = GetSubjectListUseCase(repo)

    @Provides
    fun provideGetFruitListUseCase(
        repo: FruitRepository
    ): GetFruitListUseCase = GetFruitListUseCase(repo)

    @Provides
    fun provideGetPersonageUseCase(
        repo: PersonageRepository
    ): GetPersonageUseCase = GetPersonageUseCase(repo)

    @Provides
    fun provideGetPersonageDescriptionUseCase(
        repo: PersonageDescriptionRepository
    ): GetPersonageDescriptionUseCase = GetPersonageDescriptionUseCase(repo)

    @Provides
    fun provideGetCareerUseCase(
        repo: PersonageDescriptionRepository,
        bandRepo: BandRepository,
        bandPersonageRepo: BandPersonageRepository
    ): GetCareerUseCase = GetCareerUseCase(repo, bandRepo, bandPersonageRepo)

    @Provides
    fun provideGetRewardUseCase(
        repo: RewardRepository
    ): GetRewardUseCase = GetRewardUseCase(repo)

    @Provides
    fun provideGetMangaUseCase(
        repo: MangaRepository
    ): GetMangaUseCase = GetMangaUseCase(repo)

    @Provides
    fun provideGetSkillUseCase(
        repo: SkillRepository
    ): GetSkillUseCase = GetSkillUseCase(repo)

    @Provides
    fun provideGetFruitUseCase(
        repo: FruitRepository
    ): GetFruitUseCase = GetFruitUseCase(repo)

    @Provides
    fun provideGetWeaponsUseCase(
        repo: WeaponsRepository
    ): GetWeaponsUseCase = GetWeaponsUseCase(repo)
}
package com.defey.onepiecestorybase.di

import com.defey.onepiecestorybase.domain.repository.BandDescriptionRepository
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
import com.defey.onepiecestorybase.domain.repository.ShipRepository
import com.defey.onepiecestorybase.domain.repository.SkillRepository
import com.defey.onepiecestorybase.domain.repository.SubjectRepository
import com.defey.onepiecestorybase.domain.repository.WeaponsRepository
import com.defey.onepiecestorybase.domain.usecase.band.GetBandDescriptionUseCase
import com.defey.onepiecestorybase.domain.usecase.band.GetBandPersonageUseCase
import com.defey.onepiecestorybase.domain.usecase.band.GetBandUseCase
import com.defey.onepiecestorybase.domain.usecase.band.GetShipBandUseCase
import com.defey.onepiecestorybase.domain.usecase.band.SendReadBandUseCase
import com.defey.onepiecestorybase.domain.usecase.fruit.GetFruitPersonageUseCase
import com.defey.onepiecestorybase.domain.usecase.fruit.GetFruitUseCase
import com.defey.onepiecestorybase.domain.usecase.fruit.GetMangaFruitUseCase
import com.defey.onepiecestorybase.domain.usecase.fruit.SendReadFruitUseCase
import com.defey.onepiecestorybase.domain.usecase.lists.GetBandListUseCase
import com.defey.onepiecestorybase.domain.usecase.lists.GetFruitListUseCase
import com.defey.onepiecestorybase.domain.usecase.lists.GetLocationListUseCase
import com.defey.onepiecestorybase.domain.usecase.lists.GetPersonageListUseCase
import com.defey.onepiecestorybase.domain.usecase.lists.GetSubjectListUseCase
import com.defey.onepiecestorybase.domain.usecase.location.GetLocationDescriptionUseCase
import com.defey.onepiecestorybase.domain.usecase.location.GetLocationUseCase
import com.defey.onepiecestorybase.domain.usecase.location.GetMangaLocationUseCase
import com.defey.onepiecestorybase.domain.usecase.location.GetPersonageLocationUseCase
import com.defey.onepiecestorybase.domain.usecase.location.GetSubjectLocationUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetCareerUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetPersonageFruitUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetMangaUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetPersonageDescriptionUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetPersonageUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetRewardUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetSkillUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetWeaponsUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.SendReadPersonageUseCase
import com.defey.onepiecestorybase.domain.usecase.place.DeleteOldAvatarPlaceUseCase
import com.defey.onepiecestorybase.domain.usecase.place.GetIslandsUseCase
import com.defey.onepiecestorybase.domain.usecase.place.GetLastPlaceUseCase
import com.defey.onepiecestorybase.domain.usecase.place.GetAvatarPlaceUseCase
import com.defey.onepiecestorybase.domain.usecase.place.GetNextTimeUseCase
import com.defey.onepiecestorybase.domain.usecase.location.SendReadLocationUseCase
import com.defey.onepiecestorybase.domain.usecase.place.SyncMapUseCase
import com.defey.onepiecestorybase.domain.usecase.place.SynchronizeIslandUseCase
import com.defey.onepiecestorybase.domain.usecase.place.SynchronizePersonageIslandUseCase
import com.defey.onepiecestorybase.domain.usecase.subject.GetMangaSubjectUseCase
import com.defey.onepiecestorybase.domain.usecase.subject.GetSubjectUseCase
import com.defey.onepiecestorybase.domain.usecase.subject.SendReadSubjectUseCase
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
        GetPersonageListUseCase(
            personageRepo,
            personageDescriptionRepo,
            bandRepo,
            bandPersonageRepo
        )

    @Provides
    fun provideGetBandListUseCase(
        bandRepo: BandRepository,
        bandDescriptionRepo: BandDescriptionRepository
    ): GetBandListUseCase = GetBandListUseCase(bandRepo, bandDescriptionRepo)

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
    fun provideGetPersonageFruitUseCase(
        repo: FruitRepository
    ): GetPersonageFruitUseCase = GetPersonageFruitUseCase(repo)

    @Provides
    fun provideGetWeaponsUseCase(
        repo: WeaponsRepository
    ): GetWeaponsUseCase = GetWeaponsUseCase(repo)

    @Provides
    fun provideGetLocationUseCase(
        repo: PlaceRepository
    ): GetLocationUseCase = GetLocationUseCase(repo)

    @Provides
    fun provideGetLocationDescriptionUseCase(
        repo: PlaceDescriptionRepository
    ): GetLocationDescriptionUseCase = GetLocationDescriptionUseCase(repo)

    @Provides
    fun provideGetPersonageLocationUseCase(
        repo: PersonageRepository,
        repoDescription: PersonageDescriptionRepository
    ): GetPersonageLocationUseCase = GetPersonageLocationUseCase(repo, repoDescription)

    @Provides
    fun provideGetSubjectLocationUseCase(
        repo: SubjectRepository
    ): GetSubjectLocationUseCase = GetSubjectLocationUseCase(repo)

    @Provides
    fun provideGetMangaLocationUseCase(
        repo: MangaRepository
    ): GetMangaLocationUseCase = GetMangaLocationUseCase(repo)

    @Provides
    fun provideGetBandUseCase(
        repo: BandRepository
    ): GetBandUseCase = GetBandUseCase(repo)

    @Provides
    fun provideGetBandDescriptionUseCase(
        repo: BandDescriptionRepository
    ): GetBandDescriptionUseCase = GetBandDescriptionUseCase(repo)

    @Provides
    fun provideGetBandPersonageUseCase(
        repo: BandPersonageRepository,
        personageRepo: PersonageRepository,
        personageDescriptionRepo: PersonageDescriptionRepository
    ): GetBandPersonageUseCase = GetBandPersonageUseCase(repo, personageRepo, personageDescriptionRepo)

    @Provides
    fun provideGetShipBandUseCase(
        repo: ShipRepository
    ): GetShipBandUseCase = GetShipBandUseCase(repo)

    @Provides
    fun provideGetFruitUseCase(
        repo: FruitRepository
    ): GetFruitUseCase = GetFruitUseCase(repo)

    @Provides
    fun provideGetMangaFruitUseCase(
        repo: MangaRepository
    ): GetMangaFruitUseCase = GetMangaFruitUseCase(repo)

    @Provides
    fun provideGetFruitPersonageUseCase(
        repo: PersonageDescriptionRepository,
        personageRepo: PersonageRepository
    ): GetFruitPersonageUseCase = GetFruitPersonageUseCase(repo, personageRepo)

    @Provides
    fun provideGetSubjectUseCase(
        repo: SubjectRepository
    ): GetSubjectUseCase = GetSubjectUseCase(repo)

    @Provides
    fun provideGetMangaSubjectUseCase(
        repo: MangaRepository
    ): GetMangaSubjectUseCase = GetMangaSubjectUseCase(repo)

    @Provides
    fun provideSendReadPersonageUseCase(
        repo: PersonageDescriptionRepository
    ): SendReadPersonageUseCase = SendReadPersonageUseCase(repo)

    @Provides
    fun provideSendReadBandUseCase(
        repo: BandDescriptionRepository
    ): SendReadBandUseCase = SendReadBandUseCase(repo)

    @Provides
    fun provideSendReadLocationUseCase(
        repo: PlaceDescriptionRepository
    ): SendReadLocationUseCase = SendReadLocationUseCase(repo)

    @Provides
    fun provideSendReadSubjectUseCase(
        repo: SubjectRepository
    ): SendReadSubjectUseCase = SendReadSubjectUseCase(repo)

    @Provides
    fun provideSendReadFruitUseCase(
        repo: FruitRepository
    ): SendReadFruitUseCase = SendReadFruitUseCase(repo)

}
package com.defey.onepiecestorybase.di

import com.defey.onepiecestorybase.data.local.localDataSource.BandDescriptionLocalDataSource
import com.defey.onepiecestorybase.data.local.localDataSource.BandDescriptionLocalDataSourceImpl
import com.defey.onepiecestorybase.data.local.localDataSource.BandLocalDataSource
import com.defey.onepiecestorybase.data.local.localDataSource.BandLocalDataSourceImpl
import com.defey.onepiecestorybase.data.local.localDataSource.BandPersonageLocalDataSource
import com.defey.onepiecestorybase.data.local.localDataSource.BandPersonageLocalDataSourceImpl
import com.defey.onepiecestorybase.data.local.localDataSource.BondLocalDataSource
import com.defey.onepiecestorybase.data.local.localDataSource.BondLocalDataSourceImpl
import com.defey.onepiecestorybase.data.local.localDataSource.FruitLocalDataSource
import com.defey.onepiecestorybase.data.local.localDataSource.FruitLocalDataSourceImpl
import com.defey.onepiecestorybase.data.local.localDataSource.IslandLocalDataSource
import com.defey.onepiecestorybase.data.local.localDataSource.IslandLocalDataSourceImpl
import com.defey.onepiecestorybase.data.local.localDataSource.MangaLocalDataSource
import com.defey.onepiecestorybase.data.local.localDataSource.MangaLocalDataSourceImpl
import com.defey.onepiecestorybase.data.local.localDataSource.PersonageDescriptionLocalDataSource
import com.defey.onepiecestorybase.data.local.localDataSource.PersonageDescriptionLocalDataSourceImpl
import com.defey.onepiecestorybase.data.local.localDataSource.PersonageLocalDataSource
import com.defey.onepiecestorybase.data.local.localDataSource.PersonageLocalDataSourceImpl
import com.defey.onepiecestorybase.data.local.localDataSource.PlaceDescriptionLocalDataSource
import com.defey.onepiecestorybase.data.local.localDataSource.PlaceDescriptionLocalDataSourceImpl
import com.defey.onepiecestorybase.data.local.localDataSource.PlaceItemLocalDataSource
import com.defey.onepiecestorybase.data.local.localDataSource.PlaceItemLocalDataSourceImpl
import com.defey.onepiecestorybase.data.local.localDataSource.PlaceLocalDataSource
import com.defey.onepiecestorybase.data.local.localDataSource.PlaceLocalDataSourceImpl
import com.defey.onepiecestorybase.data.local.localDataSource.RewardLocalDataSource
import com.defey.onepiecestorybase.data.local.localDataSource.RewardLocalDataSourceImpl
import com.defey.onepiecestorybase.data.local.localDataSource.ShipLocalDataSource
import com.defey.onepiecestorybase.data.local.localDataSource.ShipLocalDataSourceImpl
import com.defey.onepiecestorybase.data.local.localDataSource.SkillLocalDataSource
import com.defey.onepiecestorybase.data.local.localDataSource.SkillLocalDataSourceImpl
import com.defey.onepiecestorybase.data.local.localDataSource.SubjectLocalDataSource
import com.defey.onepiecestorybase.data.local.localDataSource.SubjectLocalDataSourceImpl
import com.defey.onepiecestorybase.data.local.localDataSource.WeaponsLocalDataSource
import com.defey.onepiecestorybase.data.local.localDataSource.WeaponsLocalDataSourceImpl
import com.defey.onepiecestorybase.data.remote.remoteDataSource.BandDescriptionRemoteDataSource
import com.defey.onepiecestorybase.data.remote.remoteDataSource.BandDescriptionRemoteDataSourceImpl
import com.defey.onepiecestorybase.data.remote.remoteDataSource.BandPersonageRemoteDataSource
import com.defey.onepiecestorybase.data.remote.remoteDataSource.BandPersonageRemoteDataSourceImpl
import com.defey.onepiecestorybase.data.remote.remoteDataSource.BandRemoteDataSource
import com.defey.onepiecestorybase.data.remote.remoteDataSource.BandRemoteDataSourceImpl
import com.defey.onepiecestorybase.data.remote.remoteDataSource.BondRemoteDataSource
import com.defey.onepiecestorybase.data.remote.remoteDataSource.BondRemoteDataSourceImpl
import com.defey.onepiecestorybase.data.remote.remoteDataSource.FruitRemoteDataSource
import com.defey.onepiecestorybase.data.remote.remoteDataSource.FruitRemoteDataSourceImpl
import com.defey.onepiecestorybase.data.remote.remoteDataSource.IslandRemoteDataSource
import com.defey.onepiecestorybase.data.remote.remoteDataSource.IslandRemoteDataSourceImpl
import com.defey.onepiecestorybase.data.remote.remoteDataSource.MangaRemoteDataSource
import com.defey.onepiecestorybase.data.remote.remoteDataSource.MangaRemoteDataSourceImpl
import com.defey.onepiecestorybase.data.remote.remoteDataSource.PersonageDescriptionRemoteDataSource
import com.defey.onepiecestorybase.data.remote.remoteDataSource.PersonageDescriptionRemoteDataSourceImpl
import com.defey.onepiecestorybase.data.remote.remoteDataSource.PersonageRemoteDataSource
import com.defey.onepiecestorybase.data.remote.remoteDataSource.PersonageRemoteDataSourceImpl
import com.defey.onepiecestorybase.data.remote.remoteDataSource.PlaceDescriptionRemoteDataSource
import com.defey.onepiecestorybase.data.remote.remoteDataSource.PlaceDescriptionRemoteDataSourceImpl
import com.defey.onepiecestorybase.data.remote.remoteDataSource.PlaceItemRemoteDataSource
import com.defey.onepiecestorybase.data.remote.remoteDataSource.PlaceItemRemoteDataSourceImpl
import com.defey.onepiecestorybase.data.remote.remoteDataSource.PlaceRemoteDataSource
import com.defey.onepiecestorybase.data.remote.remoteDataSource.PlaceRemoteDataSourceImpl
import com.defey.onepiecestorybase.data.remote.remoteDataSource.RewardRemoteDataSource
import com.defey.onepiecestorybase.data.remote.remoteDataSource.RewardRemoteDataSourceImpl
import com.defey.onepiecestorybase.data.remote.remoteDataSource.ShipRemoteDataSource
import com.defey.onepiecestorybase.data.remote.remoteDataSource.ShipRemoteDataSourceImpl
import com.defey.onepiecestorybase.data.remote.remoteDataSource.SkillRemoteDataSource
import com.defey.onepiecestorybase.data.remote.remoteDataSource.SkillRemoteDataSourceImpl
import com.defey.onepiecestorybase.data.remote.remoteDataSource.SubjectRemoteDataSource
import com.defey.onepiecestorybase.data.remote.remoteDataSource.SubjectRemoteDataSourceImpl
import com.defey.onepiecestorybase.data.remote.remoteDataSource.WeaponsRemoteDataSource
import com.defey.onepiecestorybase.data.remote.remoteDataSource.WeaponsRemoteDataSourceImpl
import com.defey.onepiecestorybase.data.repository.BandDescriptionRepositoryImpl
import com.defey.onepiecestorybase.data.repository.BandPersonageRepositoryImpl
import com.defey.onepiecestorybase.data.repository.BandRepositoryImpl
import com.defey.onepiecestorybase.data.repository.BondRepositoryImpl
import com.defey.onepiecestorybase.data.repository.FruitRepositoryImpl
import com.defey.onepiecestorybase.data.repository.IslandRepositoryImpl
import com.defey.onepiecestorybase.data.repository.MangaRepositoryImpl
import com.defey.onepiecestorybase.data.repository.PersonageDescriptionRepositoryImpl
import com.defey.onepiecestorybase.data.repository.PersonageRepositoryImpl
import com.defey.onepiecestorybase.data.repository.PlaceDescriptionRepositoryImpl
import com.defey.onepiecestorybase.data.repository.PlaceItemRepositoryImpl
import com.defey.onepiecestorybase.data.repository.PlaceRepositoryImpl
import com.defey.onepiecestorybase.data.repository.RewardRepositoryImpl
import com.defey.onepiecestorybase.data.repository.ShipRepositoryImpl
import com.defey.onepiecestorybase.data.repository.SkillRepositoryImpl
import com.defey.onepiecestorybase.data.repository.SubjectRepositoryImpl
import com.defey.onepiecestorybase.data.repository.WeaponsRepositoryImpl
import com.defey.onepiecestorybase.domain.repository.BandDescriptionRepository
import com.defey.onepiecestorybase.domain.repository.BandPersonageRepository
import com.defey.onepiecestorybase.domain.repository.BandRepository
import com.defey.onepiecestorybase.domain.repository.BondRepository
import com.defey.onepiecestorybase.domain.repository.FruitRepository
import com.defey.onepiecestorybase.domain.repository.IslandRepository
import com.defey.onepiecestorybase.domain.repository.MangaRepository
import com.defey.onepiecestorybase.domain.repository.PersonageDescriptionRepository
import com.defey.onepiecestorybase.domain.repository.PersonageRepository
import com.defey.onepiecestorybase.domain.repository.PlaceDescriptionRepository
import com.defey.onepiecestorybase.domain.repository.PlaceItemRepository
import com.defey.onepiecestorybase.domain.repository.PlaceRepository
import com.defey.onepiecestorybase.domain.repository.RewardRepository
import com.defey.onepiecestorybase.domain.repository.ShipRepository
import com.defey.onepiecestorybase.domain.repository.SkillRepository
import com.defey.onepiecestorybase.domain.repository.SubjectRepository
import com.defey.onepiecestorybase.domain.repository.WeaponsRepository
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
    abstract fun provideIslandRemoteDataSource(impl: IslandRemoteDataSourceImpl): IslandRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideIslandLocalDataSource(impl: IslandLocalDataSourceImpl): IslandLocalDataSource

    @Binds
    @Singleton
    abstract fun provideIslandRepository(impl: IslandRepositoryImpl): IslandRepository

    @Binds
    @Singleton
    abstract fun providePersonageRepository(impl: PersonageRepositoryImpl): PersonageRepository

    @Binds
    @Singleton
    abstract fun providePersonageLocalDataSource(impl: PersonageLocalDataSourceImpl): PersonageLocalDataSource

    @Binds
    @Singleton
    abstract fun providePersonageRemoteDataSource(impl: PersonageRemoteDataSourceImpl): PersonageRemoteDataSource

    @Binds
    @Singleton
    abstract fun providePersonageDescriptionLocalDataSource(impl: PersonageDescriptionLocalDataSourceImpl): PersonageDescriptionLocalDataSource

    @Binds
    @Singleton
    abstract fun providePersonageDescriptionRemoteDataSource(impl: PersonageDescriptionRemoteDataSourceImpl): PersonageDescriptionRemoteDataSource

    @Binds
    @Singleton
    abstract fun providePersonageDescriptionRepository(impl: PersonageDescriptionRepositoryImpl): PersonageDescriptionRepository

    @Binds
    @Singleton
    abstract fun provideBandLocalDataSource(impl: BandLocalDataSourceImpl): BandLocalDataSource

    @Binds
    @Singleton
    abstract fun provideBandRemoteDataSource(impl: BandRemoteDataSourceImpl): BandRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideBandRepository(impl: BandRepositoryImpl): BandRepository

    @Binds
    @Singleton
    abstract fun provideSubjectLocalDataSource(impl: SubjectLocalDataSourceImpl): SubjectLocalDataSource

    @Binds
    @Singleton
    abstract fun provideSubjectRemoteDataSource(impl: SubjectRemoteDataSourceImpl): SubjectRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideSubjectRepository(impl: SubjectRepositoryImpl): SubjectRepository

    @Binds
    @Singleton
    abstract fun provideFruitLocalDataSource(impl: FruitLocalDataSourceImpl): FruitLocalDataSource

    @Binds
    @Singleton
    abstract fun provideFruitRemoteDataSource(impl: FruitRemoteDataSourceImpl): FruitRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideFruitRepository(impl: FruitRepositoryImpl): FruitRepository

    @Binds
    @Singleton
    abstract fun provideBandPersonageLocalDataSource(impl: BandPersonageLocalDataSourceImpl): BandPersonageLocalDataSource

    @Binds
    @Singleton
    abstract fun provideBandPersonageRemoteDataSource(impl: BandPersonageRemoteDataSourceImpl): BandPersonageRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideBandPersonageRepository(impl: BandPersonageRepositoryImpl): BandPersonageRepository

    @Binds
    @Singleton
    abstract fun providePlaceDescriptionLocalDataSource(impl: PlaceDescriptionLocalDataSourceImpl): PlaceDescriptionLocalDataSource

    @Binds
    @Singleton
    abstract fun providePlaceDescriptionRemoteDataSource(impl: PlaceDescriptionRemoteDataSourceImpl): PlaceDescriptionRemoteDataSource

    @Binds
    @Singleton
    abstract fun providePlaceDescriptionRepository(impl: PlaceDescriptionRepositoryImpl): PlaceDescriptionRepository

    @Binds
    @Singleton
    abstract fun provideRewardLocalDataSource(impl: RewardLocalDataSourceImpl): RewardLocalDataSource

    @Binds
    @Singleton
    abstract fun provideRewardRemoteDataSource(impl: RewardRemoteDataSourceImpl): RewardRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideRewardRepository(impl: RewardRepositoryImpl): RewardRepository

    @Binds
    @Singleton
    abstract fun provideMangaLocalDataSource(impl: MangaLocalDataSourceImpl): MangaLocalDataSource

    @Binds
    @Singleton
    abstract fun provideMangaRemoteDataSource(impl: MangaRemoteDataSourceImpl): MangaRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideMangaRepository(impl: MangaRepositoryImpl): MangaRepository

    @Binds
    @Singleton
    abstract fun provideSkillLocalDataSource(impl: SkillLocalDataSourceImpl): SkillLocalDataSource

    @Binds
    @Singleton
    abstract fun provideSkillRemoteDataSource(impl: SkillRemoteDataSourceImpl): SkillRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideSkillRepository(impl: SkillRepositoryImpl): SkillRepository

    @Binds
    @Singleton
    abstract fun provideWeaponsLocalDataSource(impl: WeaponsLocalDataSourceImpl): WeaponsLocalDataSource

    @Binds
    @Singleton
    abstract fun provideWeaponsRemoteDataSource(impl: WeaponsRemoteDataSourceImpl): WeaponsRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideWeaponsRepository(impl: WeaponsRepositoryImpl): WeaponsRepository

    @Binds
    @Singleton
    abstract fun providePlaceLocalDataSource(impl: PlaceLocalDataSourceImpl): PlaceLocalDataSource

    @Binds
    @Singleton
    abstract fun provideBandDescriptionLocalDataSource(impl: BandDescriptionLocalDataSourceImpl): BandDescriptionLocalDataSource

    @Binds
    @Singleton
    abstract fun provideBandDescriptionRemoteDataSource(impl: BandDescriptionRemoteDataSourceImpl): BandDescriptionRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideBandDescriptionRepository(impl: BandDescriptionRepositoryImpl): BandDescriptionRepository

    @Binds
    @Singleton
    abstract fun provideShipLocalDataSource(impl: ShipLocalDataSourceImpl): ShipLocalDataSource

    @Binds
    @Singleton
    abstract fun provideShipRemoteDataSource(impl: ShipRemoteDataSourceImpl): ShipRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideShipRepository(impl: ShipRepositoryImpl): ShipRepository

    @Binds
    @Singleton
    abstract fun provideBondLocalDataSource(impl: BondLocalDataSourceImpl): BondLocalDataSource

    @Binds
    @Singleton
    abstract fun provideBondRemoteDataSource(impl: BondRemoteDataSourceImpl): BondRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideBondRepository(impl: BondRepositoryImpl): BondRepository

    @Binds
    @Singleton
    abstract fun providePlaceItemLocalDataSource(impl: PlaceItemLocalDataSourceImpl): PlaceItemLocalDataSource

    @Binds
    @Singleton
    abstract fun providePlaceItemRemoteDataSource(impl: PlaceItemRemoteDataSourceImpl): PlaceItemRemoteDataSource

    @Binds
    @Singleton
    abstract fun providePlaceItemRepository(impl: PlaceItemRepositoryImpl): PlaceItemRepository

    @Binds
    @Singleton
    abstract fun providePlaceRemoteDataSource(impl: PlaceRemoteDataSourceImpl): PlaceRemoteDataSource
}
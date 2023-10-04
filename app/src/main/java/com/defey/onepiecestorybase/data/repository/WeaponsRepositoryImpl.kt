package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.WeaponsLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomainModel
import com.defey.onepiecestorybase.data.remote.remoteDataSource.WeaponsRemoteDataSource
import com.defey.onepiecestorybase.domain.model.Weapons
import com.defey.onepiecestorybase.domain.repository.WeaponsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeaponsRepositoryImpl @Inject constructor(
    private val remote: WeaponsRemoteDataSource,
    private val local: WeaponsLocalDataSource
) : WeaponsRepository {
    override fun getWeapons(personageId: Int): Flow<List<Weapons>> {
        return local.getWeapons(personageId).map { list -> list.map { it.asDomainModel() } }
    }
}
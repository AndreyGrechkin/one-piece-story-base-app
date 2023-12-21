package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.FruitLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomainModel
import com.defey.onepiecestorybase.data.remote.model.toEntity
import com.defey.onepiecestorybase.data.remote.remoteDataSource.FruitRemoteDataSource
import com.defey.onepiecestorybase.domain.model.Fruit
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.FruitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FruitRepositoryImpl @Inject constructor(
    private val remote: FruitRemoteDataSource,
    private val local: FruitLocalDataSource
) : FruitRepository {
    override suspend fun syncFruitByPlace(placeId: Int): Response<Unit> {
        return safeApiCall {
            val fruit = remote.getFruitByPlace(placeId).response
            if (fruit.isNotEmpty()) {
                local.addFruit(fruit.map { it.toEntity() })
            }
        }
    }

    override fun getAllFruit(): Flow<List<Fruit>> {
        return local.getAllFruit().map { list -> list.map { it.asDomainModel() } }
    }

    override fun getFruit(fruitId: Int): Flow<Fruit?> {
        return local.getFruit(fruitId).map { it?.asDomainModel() }
    }

    override suspend fun sendReadFruit(fruitId: Int) {
        local.sendReadFruit(fruitId)
    }
}
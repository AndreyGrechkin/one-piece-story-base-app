package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Fruit
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.SafeApiCall
import kotlinx.coroutines.flow.Flow

interface FruitRepository : SafeApiCall {
    suspend fun syncFruitByPlace(placeId: Int): Response<Unit>
    fun getAllFruit(): Flow<List<Fruit>>
    fun getFruit(fruitId: Int): Flow<Fruit?>
    suspend fun sendReadFruit(fruitId: Int)
}
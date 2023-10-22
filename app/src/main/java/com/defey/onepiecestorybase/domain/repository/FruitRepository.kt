package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.Fruit
import kotlinx.coroutines.flow.Flow

interface FruitRepository {
    fun getAllFruit(): Flow<List<Fruit>>
    fun getFruit(fruitId: Int): Flow<Fruit?>
    suspend fun sendReadFruit(fruitId: Int)
}
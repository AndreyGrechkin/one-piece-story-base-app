package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.FruitDao
import com.defey.onepiecestorybase.data.local.model.FruitEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FruitLocalDataSource {
    fun getAllFruit(): Flow<List<FruitEntity>>
    fun getFruit(fruitId: Int): Flow<FruitEntity?>
    suspend fun sendReadFruit(fruitId: Int)
}

class FruitLocalDataSourceImpl @Inject constructor(
    private val dao: FruitDao
) : FruitLocalDataSource {
    override fun getAllFruit(): Flow<List<FruitEntity>> {
        return dao.getAllFruit()
    }

    override fun getFruit(fruitId: Int): Flow<FruitEntity?> {
        return dao.getFruit(fruitId)
    }

    override suspend fun sendReadFruit(fruitId: Int) {
        dao.sendReadFruit(fruitId)
    }

}
package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.BondDao
import com.defey.onepiecestorybase.data.local.model.BondEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface BondLocalDataSource {
    suspend fun addBond(bonds: List<BondEntity>)
    fun getAllBond(): Flow<List<BondEntity>>
}

class BondLocalDataSourceImpl @Inject constructor(
    private val dao: BondDao
) : BondLocalDataSource {
    override suspend fun addBond(bonds: List<BondEntity>) {
        dao.addBond(bonds)
    }

    override fun getAllBond(): Flow<List<BondEntity>> {
        return dao.getAllBond()
    }
}
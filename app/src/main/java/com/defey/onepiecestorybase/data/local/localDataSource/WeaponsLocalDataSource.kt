package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.PersonageWeaponsDao
import com.defey.onepiecestorybase.data.local.model.PersonageWeaponsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface WeaponsLocalDataSource {
    suspend fun addWeapon(weapons: List<PersonageWeaponsEntity>)
    fun getWeapons(personageId: Int): Flow<List<PersonageWeaponsEntity>>
}

class WeaponsLocalDataSourceImpl @Inject constructor(
    private val dao: PersonageWeaponsDao
) : WeaponsLocalDataSource {
    override suspend fun addWeapon(weapons: List<PersonageWeaponsEntity>) {
        dao.addPersonageWeapon(weapons)
    }

    override fun getWeapons(personageId: Int): Flow<List<PersonageWeaponsEntity>> {
        return dao.getWeapons(personageId)
    }
}
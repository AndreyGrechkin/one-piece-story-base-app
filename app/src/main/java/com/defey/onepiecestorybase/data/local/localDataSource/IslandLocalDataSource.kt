package com.defey.onepiecestorybase.data.local.localDataSource

import com.defey.onepiecestorybase.data.local.database.dao.IslandDao
import com.defey.onepiecestorybase.data.local.model.IslandEntity
import com.defey.onepiecestorybase.data.local.model.IslandTransitEntity
import com.defey.onepiecestorybase.data.local.model.PersonageIslandEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IslandLocalDataSource {
    suspend fun insertIsland(island: List<IslandEntity>)
    fun getIsland(): Flow<List<IslandEntity>>
    suspend fun insertPersonageIsland(personageIsland: List<PersonageIslandEntity>)
    suspend fun insertIslandTransit(islandTransit: List<IslandTransitEntity>)
    fun getPersonageIsland(): Flow<List<PersonageIslandEntity>>
    fun getIslandTransit(): Flow<List<IslandTransitEntity>>
    suspend fun deleteLocalOldAvatarPlace(lastPlaceId: Int, avatarName: String)
}

class IslandLocalDataSourceImpl @Inject constructor(
    private val dao: IslandDao
) : IslandLocalDataSource {
    override suspend fun insertIsland(island: List<IslandEntity>) {
        dao.addIsland(island)
    }

    override fun getIsland(): Flow<List<IslandEntity>> {
        return dao.getIsland()
    }

    override suspend fun insertPersonageIsland(personageIsland: List<PersonageIslandEntity>) {
        dao.addPersonageIsland(personageIsland)
    }

    override suspend fun insertIslandTransit(islandTransit: List<IslandTransitEntity>) {
        dao.addIslandTransit(islandTransit)
    }

    override fun getPersonageIsland(): Flow<List<PersonageIslandEntity>> {
        return dao.getPersonageIsland()
    }

    override fun getIslandTransit(): Flow<List<IslandTransitEntity>> {
        return dao.getIslandTransit()
    }

    override suspend fun deleteLocalOldAvatarPlace(lastPlaceId: Int, avatarName: String) {
       dao.deleteOldAvatar(lastPlaceId, avatarName)
    }
}
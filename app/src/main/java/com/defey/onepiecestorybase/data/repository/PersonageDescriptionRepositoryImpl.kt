package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.PersonageDescriptionLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomainModel
import com.defey.onepiecestorybase.data.remote.remoteDataSource.PersonageDescriptionRemoteDataSource
import com.defey.onepiecestorybase.domain.model.PersonageDescription
import com.defey.onepiecestorybase.domain.repository.PersonageDescriptionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersonageDescriptionRepositoryImpl @Inject constructor(
    private val remote: PersonageDescriptionRemoteDataSource,
    private val local: PersonageDescriptionLocalDataSource
) : PersonageDescriptionRepository {
    override fun getAllPersonageDescription(): Flow<List<PersonageDescription>> {
        return local.getAllPersonageDescription()
            .map { list -> list.map { it.asDomainModel() } }
    }

    override fun getPersonageDescription(personageId: Int): Flow<List<PersonageDescription>> {
        return local.getPersonageDescription(personageId)
            .map { list -> list.map { it.asDomainModel() } }
    }

    override suspend fun getPersonageDescriptionInPlace(personageIdList: List<Int>): List<PersonageDescription> {
        return local.getPersonageDescriptionInPlace(personageIdList).map { it.asDomainModel() }
    }
}
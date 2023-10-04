package com.defey.onepiecestorybase.data.repository

import com.defey.onepiecestorybase.data.local.localDataSource.PersonageLocalDataSource
import com.defey.onepiecestorybase.data.local.model.asDomainModel
import com.defey.onepiecestorybase.data.remote.remoteDataSource.PersonageRemoteDataSource
import com.defey.onepiecestorybase.domain.model.Personage
import com.defey.onepiecestorybase.domain.repository.PersonageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersonageRepositoryImpl @Inject constructor(
    private val remote: PersonageRemoteDataSource,
    private val local: PersonageLocalDataSource
) : PersonageRepository {
    override fun getPersonageList(): Flow<List<Personage>> {
        return local.getAllPersonage().map { list -> list.map { it.asDomainModel() } }
    }

    override fun getPersonage(personageId: Int): Flow<Personage> {
        return local.getPersonage(personageId).map { value -> value.asDomainModel() }
    }
}
package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import com.defey.onepiecestorybase.data.remote.model.PersonageDescriptionApiResponse
import javax.inject.Inject

interface PersonageDescriptionRemoteDataSource {
    suspend fun getPersonageDescriptionByPlace(placeId: Int): PersonageDescriptionApiResponse
}

class PersonageDescriptionRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : PersonageDescriptionRemoteDataSource {
    override suspend fun getPersonageDescriptionByPlace(placeId: Int): PersonageDescriptionApiResponse {
        return api.getPersonageDescriptionByPlace(placeId)
    }

}
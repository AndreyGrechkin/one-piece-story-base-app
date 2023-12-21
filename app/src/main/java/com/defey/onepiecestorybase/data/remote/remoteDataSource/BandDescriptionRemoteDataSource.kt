package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import com.defey.onepiecestorybase.data.remote.model.BandDescriptionApiResponse
import javax.inject.Inject

interface BandDescriptionRemoteDataSource {
    suspend fun getBandDescriptionByPlace(placeId: Int): BandDescriptionApiResponse
}

class BandDescriptionRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : BandDescriptionRemoteDataSource {
    override suspend fun getBandDescriptionByPlace(placeId: Int): BandDescriptionApiResponse {
        return api.getBandDescriptionByPlace(placeId)
    }

}
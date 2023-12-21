package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import com.defey.onepiecestorybase.data.remote.model.BandApiResponse
import javax.inject.Inject

interface BandRemoteDataSource {
    suspend fun getBandByPlace(placeId: Int): BandApiResponse
}

class BandRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : BandRemoteDataSource {
    override suspend fun getBandByPlace(placeId: Int): BandApiResponse {
        return api.getBandByPlace(placeId)
    }

}
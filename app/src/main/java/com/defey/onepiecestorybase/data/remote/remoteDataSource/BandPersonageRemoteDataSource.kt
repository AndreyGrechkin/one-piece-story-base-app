package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import com.defey.onepiecestorybase.data.remote.model.BandPersonageApiResponse
import javax.inject.Inject

interface BandPersonageRemoteDataSource {
    suspend fun getBandPersonageByPlace(placeId: Int): BandPersonageApiResponse
}

class BandPersonageRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : BandPersonageRemoteDataSource {
    override suspend fun getBandPersonageByPlace(placeId: Int): BandPersonageApiResponse {
        return api.getBandPersonageByPlace(placeId)
    }

}
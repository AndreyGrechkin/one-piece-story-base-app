package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import com.defey.onepiecestorybase.data.remote.model.PlaceResponse
import javax.inject.Inject

interface PlaceRemoteDataSource {
    suspend fun getPLaceById(placeId: Int): PlaceResponse
}

class PlaceRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : PlaceRemoteDataSource {
    override suspend fun getPLaceById(placeId: Int): PlaceResponse {
        return api.getPlaceById(placeId)
    }

}
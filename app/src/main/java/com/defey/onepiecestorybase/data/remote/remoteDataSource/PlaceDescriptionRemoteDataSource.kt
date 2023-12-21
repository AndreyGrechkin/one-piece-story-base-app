package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import com.defey.onepiecestorybase.data.remote.model.PlaceDescriptionApiResponse
import javax.inject.Inject

interface PlaceDescriptionRemoteDataSource {
    suspend fun getPlaceDescriptionByPlace(placeId: Int): PlaceDescriptionApiResponse
}

class PlaceDescriptionRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : PlaceDescriptionRemoteDataSource {
    override suspend fun getPlaceDescriptionByPlace(placeId: Int): PlaceDescriptionApiResponse {
        return api.getPlaceDescriptionByPlace(placeId)
    }

}
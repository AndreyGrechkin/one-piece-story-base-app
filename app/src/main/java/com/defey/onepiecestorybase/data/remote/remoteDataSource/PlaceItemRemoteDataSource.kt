package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import com.defey.onepiecestorybase.data.remote.model.PlaceItemApiResponse
import com.defey.onepiecestorybase.data.remote.model.PlaceTransitItemApiResponse
import javax.inject.Inject

interface PlaceItemRemoteDataSource {
    suspend fun getPlaceItemByPlace(placeId: Int): PlaceItemApiResponse
    suspend fun getPlaceItemTransitByPlace(placeId: Int): PlaceTransitItemApiResponse
}

class PlaceItemRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : PlaceItemRemoteDataSource {
    override suspend fun getPlaceItemByPlace(placeId: Int): PlaceItemApiResponse {
        return api.getPlaceItemByPlace(placeId)
    }

    override suspend fun getPlaceItemTransitByPlace(placeId: Int): PlaceTransitItemApiResponse {
        return api.getPlaceItemTransitByPlace(placeId)
    }
}
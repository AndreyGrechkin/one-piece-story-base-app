package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import com.defey.onepiecestorybase.data.remote.model.ShipApiResponse
import javax.inject.Inject

interface ShipRemoteDataSource {
    suspend fun getShipByPlace(placeId: Int): ShipApiResponse
}

class ShipRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : ShipRemoteDataSource {
    override suspend fun getShipByPlace(placeId: Int): ShipApiResponse {
        return api.getShipByPlace(placeId)
    }

}
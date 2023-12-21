package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import com.defey.onepiecestorybase.data.remote.model.FruitApiResponse
import javax.inject.Inject

interface FruitRemoteDataSource {
    suspend fun getFruitByPlace(placeId: Int): FruitApiResponse
}

class FruitRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : FruitRemoteDataSource {
    override suspend fun getFruitByPlace(placeId: Int): FruitApiResponse {
        return api.getFruitByPlace(placeId)
    }

}
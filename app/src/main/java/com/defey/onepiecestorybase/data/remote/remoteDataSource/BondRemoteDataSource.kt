package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import com.defey.onepiecestorybase.data.remote.model.BondApiResponse
import javax.inject.Inject

interface BondRemoteDataSource {
    suspend fun getBondByPlace(placeId: Int): BondApiResponse
}

class BondRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : BondRemoteDataSource {
    override suspend fun getBondByPlace(placeId: Int): BondApiResponse {
        return api.getBondByPlace(placeId)
    }
}
package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import com.defey.onepiecestorybase.data.remote.model.PersonageRewardApiResponse
import javax.inject.Inject

interface RewardRemoteDataSource {
    suspend fun getRewardByPlace(placeId: Int): PersonageRewardApiResponse
}

class RewardRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : RewardRemoteDataSource {
    override suspend fun getRewardByPlace(placeId: Int): PersonageRewardApiResponse {
        return api.getRewardByPlace(placeId)
    }

}
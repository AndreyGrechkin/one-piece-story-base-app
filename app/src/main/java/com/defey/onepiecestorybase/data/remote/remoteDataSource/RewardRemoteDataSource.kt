package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import javax.inject.Inject

interface RewardRemoteDataSource {
}

class RewardRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : RewardRemoteDataSource {

}
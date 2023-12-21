package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import com.defey.onepiecestorybase.data.remote.model.PersonageSkillApiResponse
import javax.inject.Inject

interface SkillRemoteDataSource {
    suspend fun getSkillByPlace(placeId: Int): PersonageSkillApiResponse
}

class SkillRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : SkillRemoteDataSource {
    override suspend fun getSkillByPlace(placeId: Int): PersonageSkillApiResponse {
        return api.getSkillByPlace(placeId)
    }

}
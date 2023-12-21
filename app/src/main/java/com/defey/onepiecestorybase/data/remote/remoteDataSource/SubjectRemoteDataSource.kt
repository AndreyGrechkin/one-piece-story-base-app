package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import com.defey.onepiecestorybase.data.remote.model.SubjectApiResponse
import javax.inject.Inject

interface SubjectRemoteDataSource {
    suspend fun getSubjectByPlace(placeId: Int): SubjectApiResponse
}

class SubjectRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : SubjectRemoteDataSource {
    override suspend fun getSubjectByPlace(placeId: Int): SubjectApiResponse {
        return api.getSubjectByPlace(placeId)
    }
}
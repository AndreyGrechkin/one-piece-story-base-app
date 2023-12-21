package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import com.defey.onepiecestorybase.data.remote.model.MangaApiResponse
import javax.inject.Inject

interface MangaRemoteDataSource {
    suspend fun getMangaByPlace(placeId: Int): MangaApiResponse
}

class MangaRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : MangaRemoteDataSource {
    override suspend fun getMangaByPlace(placeId: Int): MangaApiResponse {
        return api.getMangaByPlace(placeId)
    }

}
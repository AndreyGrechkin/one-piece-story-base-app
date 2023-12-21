package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import com.defey.onepiecestorybase.data.remote.model.PersonageWeaponsApiResponse
import javax.inject.Inject

interface WeaponsRemoteDataSource {
    suspend fun getWeaponByPlace(placeId: Int): PersonageWeaponsApiResponse
}

class WeaponsRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : WeaponsRemoteDataSource {
    override suspend fun getWeaponByPlace(placeId: Int): PersonageWeaponsApiResponse {
        return api.getWeaponByPlace(placeId)
    }

}
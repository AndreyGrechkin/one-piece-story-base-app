package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.data.remote.model.MapResponse
import ovh.plrapps.mapcompose.core.TileStreamProvider

interface PlaceRepository {
    suspend fun getMapById(id: String): MapResponse
    suspend fun syncMapById(id: Int)

}
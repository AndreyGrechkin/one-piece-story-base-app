package com.defey.onepiecestorybase.domain.repository

import com.defey.onepiecestorybase.domain.model.PlaceDescription
import kotlinx.coroutines.flow.Flow

interface PlaceDescriptionRepository {
    fun getAllPlaceDescription(): Flow<List<PlaceDescription>>
}
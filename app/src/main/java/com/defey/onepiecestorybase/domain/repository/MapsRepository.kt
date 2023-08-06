package com.defey.onepiecestorybase.domain.repository

import ovh.plrapps.mapcompose.core.TileStreamProvider

interface MapsRepository {
    fun getFullMap(): TileStreamProvider
}
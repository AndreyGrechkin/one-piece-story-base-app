package com.defey.onepiecestorybase.data.repository

import android.content.Context
import android.util.Log
import com.defey.onepiecestorybase.domain.repository.MapsRepository
import ovh.plrapps.mapcompose.core.TileStreamProvider
import javax.inject.Inject

class MapsRepositoryImpl @Inject constructor(
 val context: Context
): MapsRepository {
    override fun getFullMap(): TileStreamProvider =
    TileStreamProvider { row, col, zoomLvl ->
        try {
            //Log.d("MyLog", "zoomLvl: $zoomLvl, row: $row, col: $col")
            context.assets?.open("map/plan/$zoomLvl/$row/$col.jpg")
        } catch (e: Exception) {
            null
        }
    }
}
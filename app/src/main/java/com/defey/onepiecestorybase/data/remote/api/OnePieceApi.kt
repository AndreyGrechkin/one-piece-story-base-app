package com.defey.onepiecestorybase.data.remote.api

import com.defey.onepiecestorybase.data.remote.model.MapResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OnePieceApi {

    @GET("map")
       suspend fun getMapById(@Query("id") id: String): MapResponse

}
package com.defey.onepiecestorybase.data.remote.api

import com.defey.onepiecestorybase.data.remote.model.IslandApiResponse
import com.defey.onepiecestorybase.data.remote.model.IslandTransitResponse
import com.defey.onepiecestorybase.data.remote.model.MapResponse
import com.defey.onepiecestorybase.data.remote.model.PersonageIslandResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OnePieceApi {

    @GET("map")
    suspend fun getMapById(@Query("id") id: Int): MapResponse

    @GET("islands")
    suspend fun getIsland(): IslandApiResponse

    @GET("islands/personage")
    suspend fun getPersonageIsland(@Query("placeId") placeId: Int): PersonageIslandResponse

    @GET("islands/transit")
    suspend fun getIslandTransit(@Query("placeId") placeId: Int): IslandTransitResponse
}
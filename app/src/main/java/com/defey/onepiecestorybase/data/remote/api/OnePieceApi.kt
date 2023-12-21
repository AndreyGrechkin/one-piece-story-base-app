package com.defey.onepiecestorybase.data.remote.api

import com.defey.onepiecestorybase.data.remote.model.BandApiResponse
import com.defey.onepiecestorybase.data.remote.model.BandDescriptionApiResponse
import com.defey.onepiecestorybase.data.remote.model.BandPersonageApiResponse
import com.defey.onepiecestorybase.data.remote.model.BondApiResponse
import com.defey.onepiecestorybase.data.remote.model.FruitApiResponse
import com.defey.onepiecestorybase.data.remote.model.IslandApiResponse
import com.defey.onepiecestorybase.data.remote.model.IslandTransitResponse
import com.defey.onepiecestorybase.data.remote.model.MangaApiResponse
import com.defey.onepiecestorybase.data.remote.model.PersonageApiResponse
import com.defey.onepiecestorybase.data.remote.model.PersonageDescriptionApiResponse
import com.defey.onepiecestorybase.data.remote.model.PersonageIslandResponse
import com.defey.onepiecestorybase.data.remote.model.PersonageRewardApiResponse
import com.defey.onepiecestorybase.data.remote.model.PersonageSkillApiResponse
import com.defey.onepiecestorybase.data.remote.model.PersonageWeaponsApiResponse
import com.defey.onepiecestorybase.data.remote.model.PlaceDescriptionApiResponse
import com.defey.onepiecestorybase.data.remote.model.PlaceItemApiResponse
import com.defey.onepiecestorybase.data.remote.model.PlaceResponse
import com.defey.onepiecestorybase.data.remote.model.PlaceTransitItemApiResponse
import com.defey.onepiecestorybase.data.remote.model.ShipApiResponse
import com.defey.onepiecestorybase.data.remote.model.SubjectApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OnePieceApi {
    @GET("band/place")
    suspend fun getBandByPlace(@Query("placeId") placeId: Int): BandApiResponse

    @GET("band_description/place")
    suspend fun getBandDescriptionByPlace(@Query("placeId") placeId: Int): BandDescriptionApiResponse

    @GET("band_personage/place")
    suspend fun getBandPersonageByPlace(@Query("placeId") placeId: Int): BandPersonageApiResponse

    @GET("bond/place")
    suspend fun getBondByPlace(@Query("placeId") placeId: Int): BondApiResponse

    @GET("fruit/place")
    suspend fun getFruitByPlace(@Query("placeId") placeId: Int): FruitApiResponse

    @GET("place")
    suspend fun getPlaceById(@Query("id") id: Int): PlaceResponse

    @GET("place_description")
    suspend fun getPlaceDescriptionByPlace(@Query("id") placeId: Int): PlaceDescriptionApiResponse

    @GET("place_item")
    suspend fun getPlaceItemByPlace(@Query("id") placeId: Int): PlaceItemApiResponse

    @GET("place_transit")
    suspend fun getPlaceItemTransitByPlace(@Query("id") placeId: Int): PlaceTransitItemApiResponse

    @GET("inventory/place")
    suspend fun getSubjectByPlace(@Query("placeId") placeId: Int): SubjectApiResponse

    @GET("manga/place")
    suspend fun getMangaByPlace(@Query("placeId") placeId: Int): MangaApiResponse

    @GET("personage/place")
    suspend fun getPersonageByPlace(@Query("placeId") placeId: Int): PersonageApiResponse

    @GET("personage_description/place")
    suspend fun getPersonageDescriptionByPlace(@Query("placeId") placeId: Int): PersonageDescriptionApiResponse

    @GET("reward/place")
    suspend fun getRewardByPlace(@Query("placeId") placeId: Int): PersonageRewardApiResponse

    @GET("personage_skill/place")
    suspend fun getSkillByPlace(@Query("placeId") placeId: Int): PersonageSkillApiResponse

    @GET("personage_weapon/place")
    suspend fun getWeaponByPlace(@Query("placeId") placeId: Int): PersonageWeaponsApiResponse

    @GET("ship/place")
    suspend fun getShipByPlace(@Query("placeId") placeId: Int): ShipApiResponse

    @GET("islands")
    suspend fun getIsland(): IslandApiResponse

    @GET("islands/personage")
    suspend fun getPersonageIsland(@Query("placeId") placeId: Int): PersonageIslandResponse

    @GET("islands/transit")
    suspend fun getIslandTransit(@Query("placeId") placeId: Int): IslandTransitResponse
}
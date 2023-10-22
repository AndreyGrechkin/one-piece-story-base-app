package com.defey.onepiecestorybase.domain.model

data class LocationCompact(
    val locationId: Int,
    val placeName: String?,
    val locationName: String?,
    val sea: String?,
    val locationImage: String?,
    val isNewLocation: Boolean
)

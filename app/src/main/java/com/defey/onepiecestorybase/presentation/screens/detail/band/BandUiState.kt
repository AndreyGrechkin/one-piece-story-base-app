package com.defey.onepiecestorybase.presentation.screens.detail.band

import com.defey.onepiecestorybase.domain.model.PersonageBandContent
import com.defey.onepiecestorybase.domain.model.Ship
import com.defey.onepiecestorybase.presentation.screens.UiState

data class BandUiState(
    val nameBand: String = "",
    val imageBand: String? = null,
    val capitanName: String? = null,
    val description: String? = null,
    val personageList: List<PersonageBandContent> = emptyList(),
    val shipList: List<Ship> = emptyList()
) : UiState

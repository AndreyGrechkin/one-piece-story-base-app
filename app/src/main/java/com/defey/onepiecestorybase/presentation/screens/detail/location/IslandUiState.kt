package com.defey.onepiecestorybase.presentation.screens.detail.location

import com.defey.onepiecestorybase.domain.model.LocationPersonage
import com.defey.onepiecestorybase.domain.model.Place
import com.defey.onepiecestorybase.domain.model.Subject
import com.defey.onepiecestorybase.presentation.screens.UiState

data class IslandUiState(
    val location: Place? = null,
    val description: String = "",
    val event: String = "",
    val imageLocation: String? = null,
    val manga: String? = null,
    val anime: String? = null,
    val personageList: List<LocationPersonage> = emptyList(),
    val subjectList: List<Subject> = emptyList()
) : UiState

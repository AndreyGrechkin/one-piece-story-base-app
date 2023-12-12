package com.defey.onepiecestorybase.presentation.screens.lists

import com.defey.onepiecestorybase.domain.model.BandCompact
import com.defey.onepiecestorybase.domain.model.FruitCompact
import com.defey.onepiecestorybase.domain.model.LocationCompact
import com.defey.onepiecestorybase.domain.model.PersonageCompact
import com.defey.onepiecestorybase.domain.model.SubjectCompact
import com.defey.onepiecestorybase.presentation.screens.UiState

data class ListsUiState(
    val search: String = "",
    val personageList: List<PersonageCompact> = emptyList(),
    val bandList: List<BandCompact> = emptyList(),
    val locationList: List<LocationCompact> = emptyList(),
    val subjectList: List<SubjectCompact> = emptyList(),
    val fruitList: List<FruitCompact> = emptyList()
) : UiState

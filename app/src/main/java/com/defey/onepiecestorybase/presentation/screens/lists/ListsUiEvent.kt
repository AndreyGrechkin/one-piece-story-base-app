package com.defey.onepiecestorybase.presentation.screens.lists

import com.defey.onepiecestorybase.presentation.screens.UiEvent

sealed class ListsUiEvent : UiEvent {
    class PersonageClick(val personageId: Int) : ListsUiEvent()
    class BandClick(val bandId: Int) : ListsUiEvent()
    class LocationClick(val locationId: Int) : ListsUiEvent()
    class SubjectClick(val subjectId: Int) : ListsUiEvent()
    class FruitClick(val fruitId: Int) : ListsUiEvent()
}

package com.defey.onepiecestorybase.presentation.screens.bonds

import com.defey.onepiecestorybase.presentation.screens.UiEvent

sealed class BondsUiEvent : UiEvent {
    class ClickPersonage(val id: Int) : BondsUiEvent()
    class UnClickPersonage(val id: Int) : BondsUiEvent()
    class ClickBond(val id: Int) : BondsUiEvent()
    object ClickCloseDialog : BondsUiEvent()
}

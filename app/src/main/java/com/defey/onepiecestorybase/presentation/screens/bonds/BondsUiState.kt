package com.defey.onepiecestorybase.presentation.screens.bonds

import com.defey.onepiecestorybase.domain.model.BondContent
import com.defey.onepiecestorybase.presentation.screens.UiState

data class BondsUiState(
    val bondAllList: List<BondContent> = emptyList(),
    val bondList: List<BondContent> = emptyList(),
    val listBond: List<BondContent> = emptyList(),
    val listNoBond: List<BondContent> = emptyList(),
    val isShowBondDialog: Boolean = false,
    val dialogBond: BondWithName = BondWithName(),
    val choosePersonage: BondContent? = null,
    val hasChoosePersonage: Boolean = false,
    val search: String = ""
) : UiState

data class BondWithName(
    val firstName: String = "",
    val secondName: String = "",
    val description: String = "",
)

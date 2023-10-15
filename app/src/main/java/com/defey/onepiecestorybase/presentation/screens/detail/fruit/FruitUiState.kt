package com.defey.onepiecestorybase.presentation.screens.detail.fruit

import com.defey.onepiecestorybase.domain.model.Fruit
import com.defey.onepiecestorybase.domain.model.FruitPersonage
import com.defey.onepiecestorybase.domain.model.Manga
import com.defey.onepiecestorybase.presentation.screens.UiState

data class FruitUiState(
    val fruit: Fruit? = null,
    val manga: Manga? = null,
    val personageList: List<FruitPersonage> = emptyList()
) : UiState

package com.defey.onepiecestorybase.presentation.screens.detail.personage

import com.defey.onepiecestorybase.domain.model.Career
import com.defey.onepiecestorybase.domain.model.Fruit
import com.defey.onepiecestorybase.domain.model.Manga
import com.defey.onepiecestorybase.domain.model.Reward
import com.defey.onepiecestorybase.domain.model.Skill
import com.defey.onepiecestorybase.domain.model.Weapons
import com.defey.onepiecestorybase.presentation.screens.UiState

data class PersonageUiState(
    val namePersonage: String = "",
    val surnamePersonage: String? = null,
    val personageImage: String? = null,
    val description: String? = null,
    val careerList: List<Career> = emptyList(),
    val rewardList: List<Reward> = emptyList(),
    val skillList: List<Skill> = emptyList(),
    val weapons: List<Weapons> = emptyList(),
    val manga: Manga? = null,
    val fruit: Fruit? = null,
) : UiState

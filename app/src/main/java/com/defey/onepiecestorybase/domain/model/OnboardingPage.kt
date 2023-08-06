package com.defey.onepiecestorybase.domain.model

import androidx.annotation.DrawableRes
import com.defey.onepiecestorybase.R

sealed class OnboardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First: OnboardingPage(
        image = R.drawable.onboarding_map,
        title = "Карта мира",
        description = "Перемещаетесь вместе с героями по карте мира"
    )

    object Second: OnboardingPage(
        image = R.drawable.onboarding_bond,
        title = "Связи",
        description = "Посмотрите каккие образуются связи "
    )

    object Third: OnboardingPage(
        image = R.drawable.onboarding_info,
        title = "Информация",
        description = "Информация об островах, персонажей, команд и предметов"
    )
}

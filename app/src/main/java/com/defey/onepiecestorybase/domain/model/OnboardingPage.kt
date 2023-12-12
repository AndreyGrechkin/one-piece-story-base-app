package com.defey.onepiecestorybase.domain.model

import androidx.annotation.DrawableRes
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.presentation.utils.Constants.BONDS
import com.defey.onepiecestorybase.presentation.utils.Constants.BONDS_DESCRIPTION
import com.defey.onepiecestorybase.presentation.utils.Constants.INFORMATION
import com.defey.onepiecestorybase.presentation.utils.Constants.INFORMATION_DESCRIPTION
import com.defey.onepiecestorybase.presentation.utils.Constants.MAP_PIECE
import com.defey.onepiecestorybase.presentation.utils.Constants.MAP_PIECE_DESCRIPTION

sealed class OnboardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    data object First : OnboardingPage(
        image = R.drawable.onboarding_map,
        title = MAP_PIECE,
        description = MAP_PIECE_DESCRIPTION
    )

    data object Second : OnboardingPage(
        image = R.drawable.onboarding_bond,
        title = BONDS,
        description = BONDS_DESCRIPTION
    )

    data object Third : OnboardingPage(
        image = R.drawable.onboarding_info,
        title = INFORMATION,
        description = INFORMATION_DESCRIPTION
    )
}

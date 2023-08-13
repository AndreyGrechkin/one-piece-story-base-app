package com.defey.onepiecestorybase.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class NavTarget : Parcelable {

    @Parcelize
    object SplashScreen : NavTarget()

    @Parcelize
    object OnboardingScreen : NavTarget()

    @Parcelize
    object PlaceScreen : NavTarget()

    @Parcelize
    object ListsScreen : NavTarget()

    @Parcelize
    object BondsScreen : NavTarget()

    @Parcelize
    object InfoScreen : NavTarget()

    @Parcelize
    class IslandScreen(val islandId: Int): NavTarget()
}

fun NavTarget.name(): String = this.javaClass.kotlin.simpleName.orEmpty()
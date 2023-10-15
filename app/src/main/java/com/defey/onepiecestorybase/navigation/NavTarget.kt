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

    @Parcelize
    class PersonageScreen(val personageId: Int): NavTarget()

    @Parcelize
    class BandScreen(val bandId: Int): NavTarget()

    @Parcelize
    class FruitScreen(val fruitId: Int): NavTarget()

    @Parcelize
    class SubjectScreen(val subjectId: Int): NavTarget()
}

fun NavTarget.name(): String = this.javaClass.kotlin.simpleName.orEmpty()
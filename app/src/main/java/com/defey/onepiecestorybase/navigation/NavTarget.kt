package com.defey.onepiecestorybase.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class NavTarget : Parcelable {

    @Parcelize
    data object SplashScreen : NavTarget()

    @Parcelize
    data object OnboardingScreen : NavTarget()

    @Parcelize
    data object PlaceScreen : NavTarget()

    @Parcelize
    data object ListsScreen : NavTarget()

    @Parcelize
    data object BondsScreen : NavTarget()

    @Parcelize
    data object InfoScreen : NavTarget()

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

    @Parcelize
    class PlaceDetailScreen(val placeId: Int): NavTarget()
}

fun NavTarget.name(): String = this.javaClass.kotlin.simpleName.orEmpty()
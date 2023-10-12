package com.defey.onepiecestorybase.navigation

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.transitionhandler.rememberBackstackFader
import com.defey.onepiecestorybase.presentation.screens.bonds.BondsNode
import com.defey.onepiecestorybase.presentation.screens.detail.band.BandNode
import com.defey.onepiecestorybase.presentation.screens.detail.location.IslandNode
import com.defey.onepiecestorybase.presentation.screens.detail.personage.PersonageNode
import com.defey.onepiecestorybase.presentation.screens.info.InfoNode
import com.defey.onepiecestorybase.presentation.screens.lists.ListsNode
import com.defey.onepiecestorybase.presentation.screens.onboarding.OnboardingNode
import com.defey.onepiecestorybase.presentation.screens.place.PlaceNode
import com.defey.onepiecestorybase.presentation.screens.splash.SplashNode

class RootNode(
    buildContext: BuildContext,
    private val application: Application,
    private val backStack: BackStack<NavTarget> = BackStack(
        initialElement = NavTarget.SplashScreen,
        savedStateMap = buildContext.savedStateMap
    )
) : ParentNode<NavTarget>(
    buildContext = buildContext,
    navModel = backStack
) {

    private var navTargetName: NavTarget? by mutableStateOf(null)

    override fun resolve(navTarget: NavTarget, buildContext: BuildContext): Node {
        navTargetName = navTarget
        return when (navTarget) {
            NavTarget.SplashScreen -> SplashNode(
                buildContext,
                application,
                backStack,
            )

            NavTarget.OnboardingScreen -> OnboardingNode(
                buildContext,
                application,
                backStack,
            )

            NavTarget.PlaceScreen -> PlaceNode(
                buildContext,
                application,
                backStack,
            )

            NavTarget.ListsScreen -> ListsNode(
                buildContext,
                application,
                backStack,
            )

            NavTarget.BondsScreen -> BondsNode(
                buildContext,
                application,
                backStack,
            )

            NavTarget.InfoScreen -> InfoNode(
                buildContext,
                application,
                backStack,
            )

            is NavTarget.IslandScreen -> IslandNode(
                buildContext,
                application,
                backStack,
                defaultArgs = Bundle().apply {
                    putInt("islandId", navTarget.islandId)
                }
            )

            is NavTarget.PersonageScreen -> PersonageNode(
                buildContext,
                application,
                backStack,
                defaultArgs = Bundle().apply {
                    putInt("personageId", navTarget.personageId)
                }
            )

            is NavTarget.BandScreen -> BandNode(
                buildContext,
                application,
                backStack,
                defaultArgs = Bundle().apply {
                    putInt("bandId", navTarget.bandId)
                }
            )

        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
    @Composable
    override fun View(modifier: Modifier) {
        Children(
            navModel = backStack,
            transitionHandler = rememberBackstackFader(),
            modifier = modifier.fillMaxSize()

        )
    }
}
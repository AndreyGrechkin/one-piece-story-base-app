package com.defey.onepiecestorybase.navigation

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
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
import com.bumble.appyx.navmodel.backstack.operation.push
import com.bumble.appyx.navmodel.backstack.transitionhandler.rememberBackstackFader
import com.defey.onepiecestorybase.navigation.bottom.BottomNavItem
import com.defey.onepiecestorybase.navigation.bottom.BottomNavigationBar
import com.defey.onepiecestorybase.navigation.top.TopBarNavigation
import com.defey.onepiecestorybase.presentation.screens.bonds.BondsNode
import com.defey.onepiecestorybase.presentation.screens.island.IslandNode
import com.defey.onepiecestorybase.presentation.screens.lists.ListsNode
import com.defey.onepiecestorybase.presentation.screens.onboarding.OnboardingNode
import com.defey.onepiecestorybase.presentation.screens.place.PlaceNode
import com.defey.onepiecestorybase.presentation.screens.setting.SettingNode
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
        Log.d("MyLog", "navigate1: ${navTarget.name()}")
        navTargetName = navTarget
        Log.d("MyLog", "navigate: ${navTarget.name()}")
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

            NavTarget.SettingScreen -> SettingNode(
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

        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
    @Composable
    override fun View(modifier: Modifier) {

        Scaffold(
            topBar = {
                if (navTargetName != NavTarget.SplashScreen && navTargetName != NavTarget.PlaceScreen)
                TopBarNavigation()
                     },
            bottomBar = {
            if (navTargetName != NavTarget.SplashScreen)
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = "Place",
                        route = NavTarget.PlaceScreen,
                        icon = Icons.Default.Home
                    ),
                    BottomNavItem(
                        name = "Lists",
                        route = NavTarget.ListsScreen,
                        icon = Icons.Default.Notifications,
                        badgeCount = 23
                    ),
                    BottomNavItem(
                        name = "Bonds",
                        route = NavTarget.BondsScreen,
                        icon = Icons.Default.Settings,
                        badgeCount = 214
                    ),
                    BottomNavItem(
                        name = "Setting",
                        route = NavTarget.SettingScreen,
                        icon = Icons.Default.Settings,
                        badgeCount = 214
                    ),
                ),
                navController = navTargetName,
                onItemClick = {
                    Log.d("MyLog", "back: ${navModel}")
                    backStack.push( it.route)



                    Log.d("MyLog", "back2: ${navTargetName?.name()}")
                }
            )
        }) {
            Children(
                navModel = backStack,
                transitionHandler = rememberBackstackFader(),
                modifier = modifier.fillMaxSize().padding(top = it.calculateTopPadding())
            )
        }

        }

}
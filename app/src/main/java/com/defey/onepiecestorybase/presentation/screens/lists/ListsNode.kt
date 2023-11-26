package com.defey.onepiecestorybase.presentation.screens.lists

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.navmodel.backstack.BackStack
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.navigation.HiltComposeNode
import com.defey.onepiecestorybase.navigation.NavTarget
import com.defey.onepiecestorybase.presentation.screens.AppScreen
import com.defey.onepiecestorybase.presentation.screens.TabRowItem
import com.defey.onepiecestorybase.presentation.screens.TabTitle
import com.defey.onepiecestorybase.presentation.screens.TabbedPagerContainer
import com.defey.onepiecestorybase.presentation.screens.lists.tabs.BandTab
import com.defey.onepiecestorybase.presentation.screens.lists.tabs.FruitTab
import com.defey.onepiecestorybase.presentation.screens.lists.tabs.LocationTab
import com.defey.onepiecestorybase.presentation.screens.lists.tabs.PersonageTab
import com.defey.onepiecestorybase.presentation.screens.lists.tabs.SubjectTab

class ListsNode(
    buildContext: BuildContext,
    application: Application,
    private val backStack: BackStack<NavTarget>
) : HiltComposeNode(buildContext, application) {
    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: ListsViewModel = viewModel()

        AppScreen(
            viewModel = viewModel,
            backStack = backStack
        ) { state, onEvent ->
            val tabRowItems = mutableListOf<TabRowItem>()

            tabRowItems.add(
                TabRowItem(
                    title = { TabTitle(text = stringResource(R.string.title_list_personage)) },
                    page = {
                        PersonageTab(
                            personageList = state.personageList,
                            onEvent = onEvent
                        )
                    }
                )
            )

            tabRowItems.add(
                TabRowItem(
                    title = { TabTitle(text = stringResource(R.string.title_list_band)) },
                    page = {
                        BandTab(
                            bandList = state.bandList,
                            onEvent = onEvent
                        )
                    }
                )
            )

            tabRowItems.add(
                TabRowItem(
                    title = { TabTitle(text = stringResource(R.string.title_list_location)) },
                    page = {
                        LocationTab(
                            locationList = state.locationList,
                            onEvent = onEvent
                        )
                    }
                )
            )

            tabRowItems.add(
                TabRowItem(
                    title = { TabTitle(text = stringResource(R.string.title_list_subject)) },
                    page = {
                        SubjectTab(
                            subjectList = state.subjectList,
                            onEvent = onEvent
                        )
                    }
                )
            )

            tabRowItems.add(
                TabRowItem(
                    title = { TabTitle(text = stringResource(R.string.title_list_fruit)) },
                    page = {
                        FruitTab(
                            fruitList = state.fruitList,
                            onEvent = onEvent
                        )
                    }
                )
            )

            TabbedPagerContainer(tabRowItems = tabRowItems, scrollableTabRow = tabRowItems.size > 2)
        }
    }
}
package com.defey.onepiecestorybase.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.Icon
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumble.appyx.navmodel.backstack.BackStack
import com.defey.onepiecestorybase.navigation.NavTarget

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun <S : UiState, E : UiEvent> AppScreenTab(
    viewModel: AppViewModel<S, E>,
    backStack: BackStack<NavTarget>,
    tabTitles: List<@Composable (modifier: Modifier, state: S) -> Unit>,
    tabs: List<@Composable (modifier: Modifier, state: S, event: (E) -> Unit) -> Unit>,
    scrollable: Boolean = false
) {
    AppScreen(
        viewModel = viewModel,
        backStack = backStack
    ) { state, onEvent ->
        var delta = 0
        Surface(
            Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragStart = { delta = 0 },
                        onDragEnd = {
                            viewModel.currentTab += delta
                            viewModel.currentTab =
                                if (viewModel.currentTab >= 0) viewModel.currentTab % tabs.size else tabs.lastIndex
                        },
                        onHorizontalDrag = { _, dragAmount ->
                            when {
                                dragAmount < 0 -> delta = 1
                                dragAmount > 0 -> delta = -1
                            }
                        })
                },
           // color = ManagerTheme.colors.primaryBackground
        ) {
            Box(Modifier.fillMaxSize()) {
                Column(verticalArrangement = Arrangement.Center) {
                    if (scrollable) {
                        ScrollableTabRow(
                            edgePadding = 8.dp,
                            selectedTabIndex = viewModel.currentTab,
                    //        contentColor = ManagerTheme.colors.primaryTextColor,
                    //        backgroundColor = ManagerTheme.colors.controlColor,
                            modifier = Modifier
                                .semantics { contentDescription = "Полоса значков страниц" }
                                .requiredHeight(48.dp),
                            divider = @Composable { },
                            tabs = {
                                tabTitles.mapIndexed { index, titleState ->
                                    Tab(
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        selected = index == viewModel.currentTab,
                                        onClick = { viewModel.currentTab = index }
                                    )
                                    {
                                        titleState(Modifier, state)
                                    }
                                }
                            }
                        )
                    } else {
                        TabRow(
                            selectedTabIndex = viewModel.currentTab,
                            modifier = Modifier
                                .requiredHeight(48.dp)
                                .semantics { contentDescription = "Табы" },
                   //         backgroundColor = ManagerTheme.colors.controlColor,
                   //         contentColor = ManagerTheme.colors.primaryTextColor,
                            tabs = {
                                tabTitles.mapIndexed { index, titleState ->
                                    Row(
                                        Modifier
                                            .clickable(onClick = { viewModel.currentTab = index })
                                            .fillMaxSize()
                                    ) {
                                        titleState(
                                            Modifier
                                                .align(Alignment.CenterVertically)
                                                .fillMaxWidth(),
                                            state
                                        )
                                    }
                                }
                            }
                        )
                    }
                    tabs[viewModel.currentTab](
                        Modifier.fillMaxSize(),
                        state,
                        onEvent
                    )
                }
            }
        }
    }
}

@Composable
fun TabContent(
    modifier: Modifier = Modifier,
    label: String,
    testTag: String = "",
    icon: ImageVector? = null
) {
    Row(
        modifier = modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null)
            Icon(
                imageVector = icon,
                contentDescription = ""
            )
        Text(
            text = label,
            modifier = Modifier
                .semantics { contentDescription = testTag },
    //        color = AppTextColor.PRIMARY_TEXT_COLOR,
    //        style = AppTextStyle.BODY,
            textAlign = TextAlign.Center
        )
    }
}
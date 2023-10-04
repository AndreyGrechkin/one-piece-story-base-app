package com.defey.onepiecestorybase.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.defey.onepiecestorybase.presentation.theme.OPTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

data class TabRowItem(
    val title: @Composable () -> Unit,
    val page: @Composable () -> Unit
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabbedPagerContainer(
    tabRowItems: List<TabRowItem>,
    scrollableTabRow: Boolean = false,
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        if (scrollableTabRow) {
            ScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                contentColor = OPTheme.colors.blackColor,
                backgroundColor = OPTheme.colors.whiteColor,
                edgePadding = 10.dp,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                    )
                }
            ) {
                tabRowItems.forEachIndexed { index, tabRowItem ->
                    Tab(
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
                        selected = pagerState.currentPage == index,
                        onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                        content = { tabRowItem.title() }
                    )
                }
            }
        } else {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                contentColor = OPTheme.colors.blackColor,
                backgroundColor = OPTheme.colors.whiteColor,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                    )
                }
            ) {
                tabRowItems.forEachIndexed { index, tabRowItem ->
                    Tab(
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
                        selected = pagerState.currentPage == index,
                        onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                        content = { tabRowItem.title() }
                    )
                }
            }
        }
        HorizontalPager(
            count = tabRowItems.size,
            state = pagerState,
            userScrollEnabled = true
        ) { page ->
            tabRowItems[page].page()
        }
    }
}

@Composable
fun TabTitle(text: String) {
    Text(
        text = text,
        color = OPTheme.colors.blackColor,
        textAlign = TextAlign.Center
    )
}
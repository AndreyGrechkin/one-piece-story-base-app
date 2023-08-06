package com.defey.onepiecestorybase.presentation.screens.onboarding

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.pop
import com.defey.onepiecestorybase.domain.model.OnboardingPage
import com.defey.onepiecestorybase.navigation.NavTarget
import com.defey.onepiecestorybase.presentation.utils.Constants.ONBOARDING_PAGE_COUNT
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    onEvent: (OnboardingUiEvent) -> Unit,
    backstack: BackStack<NavTarget>
) {

    val pages = listOf(
        OnboardingPage.First,
        OnboardingPage.Second,
        OnboardingPage.Third
    )
    val pagerState = rememberPagerState()
    Log.d("MyLog", "Onboarding Screen")
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HorizontalPager(
                modifier = Modifier
                    .weight(10f),
                state = pagerState,
                count = ONBOARDING_PAGE_COUNT,
                verticalAlignment = Alignment.Top,
            ) { page ->
                PagerScreen(onboardingPage = pages[page])
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color.Blue,
                inactiveColor = Color.Gray,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterHorizontally),
                indicatorWidth = 16.dp,
                spacing = 8.dp
                )
            StartButton(modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),
                pagerState = pagerState) {
                backstack.pop()
                onEvent(OnboardingUiEvent.SaveKey(complete = true))
            }
        }
    }
}

@Composable
fun PagerScreen(onboardingPage: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(painter = painterResource(id = onboardingPage.image), contentDescription = "image page")
        Text(text = onboardingPage.title)
        Text(text = onboardingPage.description)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun StartButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {

        AnimatedVisibility(visible = pagerState.currentPage == 2) {
            Button(onClick = onClick) {
                Text(text = "Старт")
            }
        }
    }
}
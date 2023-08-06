package com.defey.onepiecestorybase.presentation.screens.splash

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.pop
import com.bumble.appyx.navmodel.backstack.operation.push
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.navigation.NavTarget

@Composable
fun SplashScreen(
    state: SplashState,
    backstack: BackStack<NavTarget>
) {
    val onboardingComplete =  state.onboardingKey
    Log.d("MyLog", "splash Screen1: $onboardingComplete")
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = state) {
        Log.d("MyLog", "launh: $onboardingComplete")
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 3000
            )
        )
        Log.d("MyLog", "splash Screen2: $onboardingComplete")
        backstack.pop()
        Log.d("MyLog", "splash Screen3: $onboardingComplete")
        if (onboardingComplete)
            backstack.push(NavTarget.PlaceScreen)
        else
            backstack.push(NavTarget.OnboardingScreen)
    }



    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.scale(scale = scale.value),
                painter = painterResource(R.drawable.ic_flag_luffy),
                contentDescription = "Logo"
            )
        }
    }
}


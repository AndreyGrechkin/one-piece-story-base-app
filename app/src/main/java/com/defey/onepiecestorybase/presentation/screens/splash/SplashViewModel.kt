package com.defey.onepiecestorybase.presentation.screens.splash

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.defey.onepiecestorybase.domain.repository.DataStorePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStore: DataStorePreferences
) : ViewModel() {

    init {
        getKey()
    }

    private val _state = mutableStateOf(SplashState())
    val state: State<SplashState> = _state

    private fun getKey() {
        dataStore.readOnboardingComplete()
            .onEach {
                _state.value = _state.value.copy(onboardingKey = it)
                Log.d("MyLog", "flow: $it")
            }
            .launchIn(viewModelScope)
    }
}
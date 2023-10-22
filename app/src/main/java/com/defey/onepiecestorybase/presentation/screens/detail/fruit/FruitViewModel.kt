package com.defey.onepiecestorybase.presentation.screens.detail.fruit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.usecase.fruit.GetFruitPersonageUseCase
import com.defey.onepiecestorybase.domain.usecase.fruit.GetFruitUseCase
import com.defey.onepiecestorybase.domain.usecase.fruit.GetMangaFruitUseCase
import com.defey.onepiecestorybase.domain.usecase.fruit.SendReadFruitUseCase
import com.defey.onepiecestorybase.presentation.screens.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FruitViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val getFruitUseCase: GetFruitUseCase,
    private val getMangaFruitUseCase: GetMangaFruitUseCase,
    private val getFruitPersonageUseCase: GetFruitPersonageUseCase,
    private val sendReadFruitUseCase: SendReadFruitUseCase
) : AppViewModel<FruitUiState, FruitUiEvent>() {

    private val fruitId = stateHandle["fruitId"] ?: 0
    private val _uiState = MutableStateFlow(FruitUiState())
    override val uiState: StateFlow<FruitUiState> = _uiState

    init {
        observeFruit()
        observePersonage()
        updateFruit()
    }

    override fun onEvent(event: FruitUiEvent) {
        when (event) {
            FruitUiEvent.CloseFruit -> navigateBack()
        }
    }

    private fun observeFruit() {
        getFruitUseCase(fruitId).onEach { response ->
            if (response is Response.Success) {
                observeManga(response.value.mangaId)
                _uiState.update { it.copy(fruit = response.value) }
            }
        }.launchIn(viewModelScope)
    }

    private fun observeManga(mangaId: Int) {
        getMangaFruitUseCase(mangaId).onEach { response ->
            if (response is Response.Success) {
                _uiState.update { it.copy(manga = response.value) }
            }
        }.launchIn(viewModelScope)
    }

    private fun observePersonage() {
        getFruitPersonageUseCase(fruitId).onEach { response ->
            if (response is Response.Success) {
                _uiState.update { it.copy(personageList = response.value) }
            }
        }.launchIn(viewModelScope)
    }

    private fun updateFruit(){
        viewModelScope.launch {
            sendReadFruitUseCase.execute(fruitId)
        }
    }
}
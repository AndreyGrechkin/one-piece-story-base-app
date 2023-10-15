package com.defey.onepiecestorybase.presentation.screens.detail.personage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.usecase.personage.GetCareerUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetPersonageFruitUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetMangaUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetPersonageDescriptionUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetPersonageUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetRewardUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetSkillUseCase
import com.defey.onepiecestorybase.domain.usecase.personage.GetWeaponsUseCase
import com.defey.onepiecestorybase.presentation.screens.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PersonageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPersonageUseCase: GetPersonageUseCase,
    private val getPersonageDescriptionUseCase: GetPersonageDescriptionUseCase,
    private val getCareerUseCase: GetCareerUseCase,
    private val getRewardUseCase: GetRewardUseCase,
    private val getMangaUseCase: GetMangaUseCase,
    private val getSkillUseCase: GetSkillUseCase,
    private val getPersonageFruitUseCase: GetPersonageFruitUseCase,
    private val getWeaponsUseCase: GetWeaponsUseCase
) : AppViewModel<PersonageUiState, PersonageUiEvent>() {

    private val _uiState = MutableStateFlow(PersonageUiState())
    override val uiState: StateFlow<PersonageUiState> = _uiState
    private val personageId: Int = savedStateHandle["personageId"] ?: 0

    init {
        observePersonage()
        observeDescription()
        observeCareer()
        observeReward()
        observeSkill()
        observeWeapons()
    }

    override fun onEvent(event: PersonageUiEvent) {
       when(event) {
           PersonageUiEvent.ClosePersonage -> navigateBack()
       }
    }

    private fun observePersonage() {
        getPersonageUseCase(personageId).onEach { response ->
            if (response is Response.Success) {
                observeManga(response.value.mangaId)
                _uiState.update { it.copy(namePersonage = response.value.name) }
            }
        }.launchIn(viewModelScope)
    }

    private fun observeDescription() {
        getPersonageDescriptionUseCase(personageId).onEach { response ->
            if (response is Response.Success) {
                val surname = response.value.findLast { it.surname != null }?.surname
                val personageImage = response.value.findLast { it.image != null }?.image
                val description = response.value.mapNotNull { it.description }.joinToString("\n")
                val fruit = response.value.find { it.fruitId != null }?.fruitId
                if (fruit != null) observeFruit(fruit)
                _uiState.update {
                    it.copy(
                        surnamePersonage = surname,
                        personageImage = personageImage,
                        description = description,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun observeCareer() {
        getCareerUseCase(personageId).onEach { response ->
            if (response is Response.Success) {
                _uiState.update { it.copy(careerList = response.value) }
            }
        }.launchIn(viewModelScope)
    }

    private fun observeReward() {
        getRewardUseCase(personageId).onEach { response ->
            if (response is Response.Success) {
                _uiState.update { it.copy(rewardList = response.value) }
            }
        }.launchIn(viewModelScope)
    }

    private fun observeManga(mangaId: Int) {
        getMangaUseCase(mangaId).onEach { response ->
            if (response is Response.Success) {
                _uiState.update { it.copy(manga = response.value) }
            }
        }.launchIn(viewModelScope)
    }

    private fun observeSkill() {
        getSkillUseCase(personageId).onEach { response ->
            if (response is Response.Success) {
                _uiState.update { it.copy(skillList = response.value) }
            }
        }.launchIn(viewModelScope)
    }

    private fun observeFruit(fruitId: Int) {
        getPersonageFruitUseCase(fruitId).onEach { response ->
            if (response is Response.Success) {
            _uiState.update { it.copy(fruit = response.value) }
            }
        }.launchIn(viewModelScope)
    }

    private fun observeWeapons(){
        getWeaponsUseCase(personageId).onEach { response ->
            if (response is Response.Success) {
                _uiState.update { it.copy(weapons = response.value) }
            }
        }.launchIn(viewModelScope)
    }

}
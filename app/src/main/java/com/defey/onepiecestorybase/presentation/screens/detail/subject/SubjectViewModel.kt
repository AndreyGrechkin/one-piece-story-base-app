package com.defey.onepiecestorybase.presentation.screens.detail.subject

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.usecase.subject.GetMangaSubjectUseCase
import com.defey.onepiecestorybase.domain.usecase.subject.GetSubjectUseCase
import com.defey.onepiecestorybase.presentation.screens.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SubjectViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val getSubjectUseCase: GetSubjectUseCase,
    private val getMangaSubjectUseCase: GetMangaSubjectUseCase
) : AppViewModel<SubjectUiState, SubjectUiEvent>() {

    private val subjectId = stateHandle["subjectId"] ?: 0
    private val _uiState = MutableStateFlow(SubjectUiState())
    override val uiState: StateFlow<SubjectUiState> = _uiState

    init {
        observeSubject()
    }

    override fun onEvent(event: SubjectUiEvent) {
        when (event) {
            SubjectUiEvent.SubjectClose -> navigateBack()
        }
    }

    private fun observeSubject() {
        getSubjectUseCase(subjectId).onEach { response ->
            if (response is Response.Success) {
                observeManga(response.value.mangaId)
                _uiState.update { it.copy(subject = response.value) }
            }
        }.launchIn(viewModelScope)
    }

    private fun observeManga(mangaId: Int) {
        getMangaSubjectUseCase(mangaId).onEach { response ->
            if (response is Response.Success) {
                _uiState.update { it.copy(manga = response.value) }
            }
        }.launchIn(viewModelScope)
    }
}
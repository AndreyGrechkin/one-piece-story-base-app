package com.defey.onepiecestorybase.presentation.screens.bonds

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.domain.model.BondContent
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.usecase.bond.GetAllBondUseCase
import com.defey.onepiecestorybase.presentation.screens.AppViewModel
import com.defey.onepiecestorybase.presentation.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BondsViewModel @Inject constructor(
    private val getAllBondUseCase: GetAllBondUseCase
) : AppViewModel<BondsUiState, BondsUiEvent>() {

    private val _uiState = MutableStateFlow(BondsUiState())
    override val uiState: StateFlow<BondsUiState> = _uiState

    private var bondList: List<BondContent> by mutableStateOf(emptyList())
    private var noListBond: List<BondContent> by mutableStateOf(emptyList())
    private var filterBond: List<BondContent> by mutableStateOf(emptyList())

    init {
        setupTopBar(
            showTopBar = true,
            title = UiText.StringResource(R.string.title_bond),
            showBackButton = false,
            actionIconResId = R.drawable.ic_flag_luffy,
            onAction = { text ->
                _uiState.update { it.copy(search = text) }
                filter(text)
            })
        setupBottomBar(isVisible = true)
        getBond()
    }

    private fun filter(text: String) {
        if (uiState.value.hasChoosePersonage) {
            val searchBond = noListBond
                .filter { it.namePersonage?.contains(text, ignoreCase = true) ?: false }
            val searchFilterBond = filterBond
                .filter { it.namePersonage?.contains(text, ignoreCase = true) ?: false }
            _uiState.update {
                it.copy(
                    listNoBond = searchBond,
                    listBond = searchFilterBond
                )
            }
        } else {
            val searchBond = bondList
                .filter { it.namePersonage?.contains(text, ignoreCase = true) ?: false }
            _uiState.update { it.copy(bondList = searchBond) }
        }
    }

    override fun onEvent(event: BondsUiEvent) {
        when (event) {
            is BondsUiEvent.ClickPersonage -> sortedBondPersonage(event.id)
            is BondsUiEvent.UnClickPersonage -> {
                _uiState.update {
                    it.copy(
                        hasChoosePersonage = false,
                        choosePersonage = null
                    )
                }
            }

            is BondsUiEvent.ClickBond -> {
                bondClick(event.id)
            }

            BondsUiEvent.ClickCloseDialog -> _uiState.update { it.copy(isShowBondDialog = false) }
        }
    }

    private fun bondClick(id: Int) {
        val bond = uiState.value.bondAllList
            .find { it.id == id }
        val bondName = BondWithName(
            firstName = bond?.nameBondPersonage.orEmpty(),
            secondName = bond?.namePersonage.orEmpty(),
            description = bond?.description.orEmpty()
        )
        _uiState.update {
            it.copy(
                isShowBondDialog = true,
                dialogBond = bondName
            )
        }
    }

    private fun getBond() {
        getAllBondUseCase().onEach { response ->
            if (response is Response.Success) {
                val bonds = response.value.distinctBy { it.personageId }
                _uiState.update { state ->
                    state.copy(
                        bondList = bonds,
                        bondAllList = response.value
                    )
                }
                bondList = bonds
            }
        }.launchIn(viewModelScope)
    }

//    private fun bondList() {
//        val bonds = uiState.value.bondAllList
//        _uiState.update { state ->
//            state.copy(bondList = bonds.distinctBy { it.personageId })
//        }
//    }

    private fun sortedBondPersonage(personageId: Int) {
        val bonds = uiState.value.bondAllList
        val personageBond = bonds.find { it.personageId == personageId }
        val bondPersonage = bonds
            .filter {
                it.bondPersonageId == personageId
            }
            .map { it.personageId }
        val sortList = bonds
            .filter { it.bondPersonageId == personageId }
            .distinctBy { it.personageId }
        val listNoBond: List<BondContent> = bonds
            .distinctBy { it.personageId }
            .filter {
                it.personageId != personageId
                        && !bondPersonage.contains(it.personageId)
                        && it.bondPersonageId != personageId
            }

        _uiState.update {
            it.copy(
                listBond = sortList,
                hasChoosePersonage = true,
                choosePersonage = personageBond,
                listNoBond = listNoBond
            )
        }
        noListBond = listNoBond
        filterBond = sortList
    }
}
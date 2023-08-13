package com.defey.onepiecestorybase.presentation.screens.lists

import android.util.Log
import androidx.compose.ui.graphics.Color
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.presentation.screens.AppViewModel
import com.defey.onepiecestorybase.presentation.screens.UpdateEventsScreen
import com.defey.onepiecestorybase.presentation.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.lang.reflect.Array.set
import javax.inject.Inject

@HiltViewModel
class ListsViewModel @Inject constructor(

) : AppViewModel<ListsUiState, ListsUiEvent>() {
    //  private val _search = MutableStateFlow(searchTextState)
    private val _state = MutableStateFlow(ListsUiState())

    //  val state: State<ListsUiState> = _state
    override val uiState: StateFlow<ListsUiState> = _state
    private val _updateScreen = MutableStateFlow(UpdateEventsScreen())

    init {
        _state.value = _state.value.copy(title = "Списки")

        setupTopBar(
            showTopBar = true,
            title = UiText.StringResource(R.string.title_list),
            showBackButton = false,
            actionIconResId = R.drawable.ic_flag_luffy,
            onAction = { text ->
                Log.d("MyLog", "action top bar $text")
                _state.update { it.copy(search = text) }
            }
        )
        setupBottomBar(isVisible = true)
    }

    override fun onEvent(event: ListsUiEvent) {
        when (event) {
            ListsUiEvent.Seteee -> {
                Log.d("MyLog", "click list")
//                setupBottomBar(update = UpdateEventsScreen().copy(bondScreen = true), isVisible = true)
//                setBottomBarUpdate(infoScreen = true)
                //   bottomList[3].copy(isNew = true)
                val listEvnt = listOf(0, 1)
                bottomList = bottomList.apply { ->
                    // Изменение элемента в списке
//                    this.value.forEachIndexed { index, item ->
//                      //  if (listEvnt.contains(index))
//                            this.value[index] = item.copy(isNew = true)
//
//                    }

                    listEvnt.forEach{
                  this.value[it].copy(isNew = true)
                    //this.value = updatedObject
                    }
//                    val updatedObject = this.value[3].copy(isNew = true)
//                    this.value[3] = updatedObject
                }

                //     _updateScreen.update { it.copy(bondScreen = true) }
            }
        }
    }
}
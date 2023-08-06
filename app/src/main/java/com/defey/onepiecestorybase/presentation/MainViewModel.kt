package com.defey.onepiecestorybase.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.defey.onepiecestorybase.data.remote.model.MapResponse
import com.defey.onepiecestorybase.domain.repository.PlaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: PlaceRepository
): ViewModel() {

    var place: MapResponse by mutableStateOf(MapResponse(
        0, null, null, 0, emptyList(), null, null, null, null, null,
        0, emptyList(), emptyList(),emptyList(),emptyList(),emptyList(),emptyList(),emptyList(),emptyList(),emptyList(),emptyList(),emptyList(),emptyList(),emptyList(),emptyList(),emptyList()
    ))

    fun getPlace(id: String){
        viewModelScope.launch {
        try {
            val map = repo.getMapById(id)
            repo.syncMapById(id.toInt())
            place = map
        } catch (e: Exception){
            Log.d("MyLog", "error: ${e.message}")
        }

        }
    }
}
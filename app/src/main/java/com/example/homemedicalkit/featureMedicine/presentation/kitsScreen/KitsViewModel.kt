package com.example.homemedicalkit.presentation.kitsScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homemedicalkit.featureMedicine.domain.useCase.kit.KitUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KitsViewModel@Inject constructor(
    val kitUseCases: KitUseCases
): ViewModel() {
    private val _state = mutableStateOf(KitState())
    val state: State<KitState> = _state
    private var getKitsJob: Job? = null

    init {
        getKits()
    }

    fun onEvent(event: KitsEvent) {
        when (event) {
            is KitsEvent.DeleteKit -> {
                viewModelScope.launch {
                    kitUseCases.deleteKit(event.kitId)
                }
            }
            else -> {}
        }
    }

    fun getKits() {
        getKitsJob?.cancel()
        getKitsJob = kitUseCases.getKits()
            .onEach { kits ->
                _state.value = state.value.copy(
                    kits = kits
                )
            }.launchIn(viewModelScope)
    }
}
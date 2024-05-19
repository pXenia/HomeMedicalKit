package com.example.homemedicalkit.presentation.kitsScreen.kitDialog

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homemedicalkit.featureMedicine.domain.model.Kit
import com.example.homemedicalkit.featureMedicine.domain.useCase.kit.KitUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddEditKitViewModel @Inject constructor(
    private val kitUseCases: KitUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _kitName = mutableStateOf(String())
    var kitName: State<String> = _kitName
    private val _kitColor = mutableStateOf(0)
    val kitColor: State<Int> = _kitColor
    private var currentKitId: Int? = null
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Int>("kitId")?.let { kitId ->
            if(kitId != -1) {
                viewModelScope.launch {
                    kitUseCases.getKit(kitId)?.also { kit ->
                        currentKitId = kit.kitId
                        _kitName.value = kit.kitName
                        _kitColor.value = kit.kitColor
                    }
                }
            }
        }
    }
    fun onEvent(event: AddEditKitEvent) {
        when (event) {
            is AddEditKitEvent.EnteredName -> {
                _kitName.value = event.value
            }
            is AddEditKitEvent.ChangeColor -> {
                _kitColor.value = event.color
            }
            is AddEditKitEvent.SaveKit -> {
                viewModelScope.launch {
                    try {
                        kitUseCases.addKit(
                            Kit(
                                kitName = kitName.value,
                                kitColor = kitColor.value,
                                kitId = currentKitId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch(e: Exception){}
                }
            }
        }
    }
    sealed class UiEvent {
        object SaveNote: UiEvent()
    }

}
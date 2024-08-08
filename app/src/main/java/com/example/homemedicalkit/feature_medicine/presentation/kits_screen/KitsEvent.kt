package com.example.homemedicalkit.presentation.kitsScreen

sealed class KitsEvent {
    data class DeleteKit(val kitId: Int?): KitsEvent()
}
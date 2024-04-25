package com.example.homemedicalkit.ViewModel

sealed class AddEditKitEvent{
    data class EnteredName(val value: String): AddEditKitEvent()
    data class ChangeColor(val color: Int): AddEditKitEvent()
    object SaveKit: AddEditKitEvent()
}
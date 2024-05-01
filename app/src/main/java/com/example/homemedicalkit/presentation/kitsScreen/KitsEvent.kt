package com.example.homemedicalkit.presentation.kitsScreen

import com.example.homemedicalkit.dataBase.Kit

sealed class KitsEvent {
    data class DeleteKit(val kit: Kit): KitsEvent()
}
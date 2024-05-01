package com.example.homemedicalkit.presentation.kitsScreen

import com.example.homemedicalkit.dataBase.Kit

data class KitState (
    val kits: List<Kit> = emptyList(),
)
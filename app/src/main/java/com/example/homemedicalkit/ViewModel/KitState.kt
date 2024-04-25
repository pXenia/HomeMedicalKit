package com.example.homemedicalkit.ViewModel

import com.example.homemedicalkit.dataBase.Kit

data class KitState (
    val kits: List<Kit> = emptyList(),
)
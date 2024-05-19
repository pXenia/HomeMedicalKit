package com.example.homemedicalkit.presentation.kitsScreen

import com.example.homemedicalkit.featureMedicine.domain.model.Kit

data class KitState (
    val kits: List<Kit> = emptyList(),
)
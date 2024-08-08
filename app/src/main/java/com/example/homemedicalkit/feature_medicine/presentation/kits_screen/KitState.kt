package com.example.homemedicalkit.presentation.kitsScreen

import com.example.homemedicalkit.feature_medicine.domain.model.Kit

data class KitState (
    val kits: List<Kit> = emptyList(),
)
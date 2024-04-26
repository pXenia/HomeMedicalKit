package com.example.homemedicalkit.ui

sealed class Screen(val route: String) {
    object MedicinesList: Screen("medicines_list")
    object MedicineCard: Screen("medicine_card")
    object KitsScreen: Screen("kits_screen")
    object KitDialog: Screen("kit_dialog")
}
package com.example.homemedicalkit.presentation.util

sealed class Screen(val route: String) {
    object MedicinesList: Screen("medicines_list")
    object MedicineCard: Screen("medicine_card")
    object KitsScreen: Screen("kits_screen")
    object KitDialog: Screen("kit_dialog")
    object DeleteDialog: Screen("delete_dialog")

}
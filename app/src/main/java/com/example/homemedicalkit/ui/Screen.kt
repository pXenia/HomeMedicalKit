package com.example.homemedicalkit.ui

sealed class Screen(val route: String) {
    object MedicinesList: Screen("medical_list")
    object MedicineCard: Screen("add_edit_medicine_screen")
}
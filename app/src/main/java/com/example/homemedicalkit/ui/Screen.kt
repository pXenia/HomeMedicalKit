package com.example.homemedicalkit.ui

sealed class Screen(val route: String) {
    object MedicineListScreen: Screen("medical_list")
    object AddEditMedicineScreen: Screen("add_edit_medicine_screen")
}
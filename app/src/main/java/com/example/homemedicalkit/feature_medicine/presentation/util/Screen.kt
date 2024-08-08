package com.example.homemedicalkit.presentation.util

sealed class Screen(val route: String) {
    object MedicinesList: Screen("medicines_list")
    object MedicineCard: Screen("medicine_card")
    object KitsScreen: Screen("kits_screen")
    object KitDialog: Screen("kit_dialog")
    object DeleteDialog: Screen("delete_dialog")
    object DeleteDialogKit: Screen("delete_dialog_kit")
    //object SignInScreen: Screen("sign_in_screen")
    //object UserScreen: Screen("user_screen")
}
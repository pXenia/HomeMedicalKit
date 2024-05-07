
package com.example.homemedicalkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.homemedicalkit.presentation.kitsScreen.KitsScreen
import com.example.homemedicalkit.presentation.kitsScreen.kitDialog.DeleteDialogKit
import com.example.homemedicalkit.presentation.kitsScreen.kitDialog.KitDialog
import com.example.homemedicalkit.presentation.medicineCard.MedicineCard
import com.example.homemedicalkit.presentation.medicinesList.MedicinesList
import com.example.homemedicalkit.presentation.medicinesList.components.DeleteDialog
import com.example.homemedicalkit.presentation.util.Screen
import com.example.homemedicalkit.ui.theme.HomeMedicalKitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeMedicalKitTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.KitsScreen.route
                ) {
                    composable(route = Screen.KitsScreen.route) {
                        KitsScreen(navController = navController)
                    }
                    composable(
                        route = Screen.MedicinesList.route + "?kitId={kitId}",
                        arguments = listOf(
                            navArgument(
                                name = "kitId"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                        )
                    ) {
                        MedicinesList(navController = navController)
                    }
                    composable(
                        route = Screen.MedicineCard.route + "?medicineId={medicineId}",
                        arguments = listOf(
                            navArgument(
                                name = "medicineId"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                        )
                    ) {
                        MedicineCard(navController = navController)
                    }
                    composable(
                        route = Screen.MedicineCard.route + "?kitId={kitId}",
                        arguments = listOf(
                            navArgument(
                                name = "kitId"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                        )
                    ) {
                        MedicineCard(navController = navController)
                    }
                    dialog(
                        route = Screen.KitDialog.route + "?kitId={kitId}",
                        arguments = listOf(
                            navArgument(
                                name = "kitId",
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                        )
                    ) {
                        KitDialog(navController = navController)
                    }
                    dialog(
                        route = Screen.DeleteDialog.route + "?medicineId={medicineId}",
                        arguments = listOf(
                            navArgument(
                                name = "medicineId"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                        )
                    ) {
                        val medicineId = it.arguments?.getInt("medicineId") ?: -1
                        DeleteDialog(medicineId = medicineId, navController = navController)
                    }
                    dialog(
                        route = Screen.DeleteDialogKit.route + "?kitId={kitId}",
                        arguments = listOf(
                            navArgument(
                                name = "kitId"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                        )
                    ) {
                        val kitId = it.arguments?.getInt("kitId") ?: -1
                        DeleteDialogKit(kitId = kitId, navController = navController)
                    }
                }
            }
        }
    }
}
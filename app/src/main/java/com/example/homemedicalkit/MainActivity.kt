
package com.example.homemedicalkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.homemedicalkit.ui.MedicineCard
import com.example.homemedicalkit.ui.MedicinesList
import com.example.homemedicalkit.ui.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.MedicinesList.route
            ) {
                composable(route = Screen.MedicinesList.route) {
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
            }
        }
    }
} 
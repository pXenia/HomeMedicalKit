
package com.example.homemedicalkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.homemedicalkit.ui.MedicineListElements
import com.example.homemedicalkit.ui.MedicineShow
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
                startDestination = Screen.MedicineListScreen.route
            ) {
                composable(route = Screen.MedicineListScreen.route) {
                    MedicineListElements(navController = navController)
                }
                composable(
                    route = Screen.AddEditMedicineScreen.route + "?medicineId={medicineId}",
                    arguments = listOf(
                        navArgument(
                            name = "medicineId"
                        ) {
                            type = NavType.IntType
                            defaultValue = -1
                        },
                    )
                ) {
                    MedicineShow(navController = navController)
                }
            }

        }
    }
} 
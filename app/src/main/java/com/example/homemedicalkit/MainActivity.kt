
package com.example.homemedicalkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.homemedicalkit.ui.KitDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KitDialog()
            /*
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.KitsScreen.route
            ) {
                composable(route = Screen.KitsScreen.route) {
                    KitsScreen(navController = navController)
                }
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
            */
        }
    }
}
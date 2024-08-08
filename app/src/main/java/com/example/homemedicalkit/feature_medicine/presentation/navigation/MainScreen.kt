package com.example.homemedicalkit.feature_medicine.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun  MainScreen(){
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.background(Color.Transparent),
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        BottomNavGraph(navController = navController)
    }
}
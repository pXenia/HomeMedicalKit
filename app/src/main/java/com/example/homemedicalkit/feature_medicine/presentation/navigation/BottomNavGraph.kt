package com.example.homemedicalkit.feature_medicine.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import com.example.homemedicalkit.presentation.kitsScreen.KitsScreen
import com.example.homemedicalkit.presentation.kitsScreen.kitDialog.DeleteDialogKit
import com.example.homemedicalkit.presentation.kitsScreen.kitDialog.KitDialog
import com.example.homemedicalkit.presentation.medicineCard.MedicineCard
import com.example.homemedicalkit.presentation.medicinesList.MedicinesList
import com.example.homemedicalkit.presentation.medicinesList.components.DeleteDialog
import com.example.homemedicalkit.presentation.util.Screen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
    navController = navController,
    startDestination = Screen.KitsScreen.route
    ) {
        /* It was used to demonstrate sign in
        composable(route = Screen.SignInScreen.route) {
            val viewModel = viewModel<SignInViewModel>()
            val state = viewModel.state.collectAsState()

            LaunchedEffect(key1 = Unit) {
                if(googleAuthUiClient.getSignedInUser() != null) {
                    navController.navigate(Screen.KitsScreen.route)
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if(result.resultCode == RESULT_OK) {
                        lifecycleScope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.value.isSignInSuccessful) {
                if(state.value.isSignInSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Вход выполнен",
                        Toast.LENGTH_LONG
                    ).show()

                    navController.navigate(Screen.KitsScreen.route)
                    viewModel.resetState()
                }
            }
            SignInScreen(
                state = state.value,
                onSignInClick = {
                    lifecycleScope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                },
                navController = navController
            )
        }
        */
        composable(route = Screen.KitsScreen.route, ) {
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
        /* It was used to demonstrate sign in
        composable(route = Screen.UserScreen.route) {
            UserScreen(userData = googleAuthUiClient.getSignedInUser(),
                onSignOut = {
                    lifecycleScope.launch {
                        googleAuthUiClient.signOut()
                        Toast.makeText(
                            applicationContext,
                            "Выход выполнен",
                            Toast.LENGTH_LONG
                        ).show()

                        navController.popBackStack()
                    }
                },
                navController = navController
            )
        }
        */
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
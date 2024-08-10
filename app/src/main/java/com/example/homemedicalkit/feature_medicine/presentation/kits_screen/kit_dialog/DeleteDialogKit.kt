package com.example.homemedicalkit.presentation.kitsScreen.kitDialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.homemedicalkit.R
import com.example.homemedicalkit.presentation.kitsScreen.KitsEvent
import com.example.homemedicalkit.presentation.kitsScreen.KitsViewModel
import com.example.homemedicalkit.presentation.util.Screen
import com.example.homemedicalkit.ui.theme.BlueSerface


@Composable
fun DeleteDialogKit(
    kitId: Int,
    navController: NavController,
    viewModel: KitsViewModel = hiltViewModel(),
    ) {
    AlertDialog(
        containerColor = BlueSerface,
        onDismissRequest = {navController.navigateUp()},
        title = {},
        text = {
            Text(
                stringResource(R.string.change_the_first_aid_kit),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            TextButton(
                {
                    viewModel.onEvent(KitsEvent.DeleteKit(kitId))
                    navController.navigateUp()
                }
            ) {
                Text(
                    text = stringResource(R.string.delete),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        },
        dismissButton = {
            TextButton(
                {
                    navController.navigate(
                        Screen.KitDialog.route + "?kitId=${kitId}")
                }
            ) {
                Text(
                    text = stringResource(R.string.change),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        },
    )
}

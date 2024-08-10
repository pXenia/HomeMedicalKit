package com.example.homemedicalkit.presentation.kitsScreen.kitDialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.homemedicalkit.R
import com.example.homemedicalkit.feature_medicine.presentation.kits_screen.components.getColorById
import com.example.homemedicalkit.presentation.util.Screen
import com.example.homemedicalkit.ui.theme.BlueSerface
import com.example.homemedicalkit.ui.theme.DarkBlueOutlined

@Composable
fun KitDialog(
    navController: NavController,
    viewModel: AddEditKitViewModel = hiltViewModel(),
) {
    AlertDialog(
        containerColor = BlueSerface,
        onDismissRequest = { navController.navigateUp() },
        title = {
            Text(
                text = stringResource(R.string.new_first_aid_kit),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        text = {
            Column {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    items(7) {
                        ColorPicker(colorId = it, viewModel = viewModel)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .background(Color.Transparent),
                    shape = RoundedCornerShape(dimensionResource(R.dimen.rounded_corner)),
                    value = viewModel.kitName.value,
                    onValueChange = {
                        viewModel.onEvent(AddEditKitEvent.EnteredName(it))
                    },
                    textStyle = MaterialTheme.typography.bodySmall,
                    placeholder = {
                        Text(
                            stringResource(R.string.title),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                )
            }
        },
        confirmButton = {
            TextButton(
                {
                    viewModel.onEvent(AddEditKitEvent.SaveKit)
                    navController.navigate(Screen.KitsScreen.route)
                }
            ) {
                Text(
                    text = stringResource(R.string.ok),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        },
        dismissButton = {
            TextButton(
                {
                    navController.navigate(Screen.KitsScreen.route)
                }
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    )
}



@Composable
fun ColorPicker(colorId: Int, viewModel: AddEditKitViewModel){
OutlinedCard(
    modifier = Modifier
        .size(32.dp)
        .clickable {
            viewModel.onEvent(AddEditKitEvent.ChangeColor(colorId))
        },
    shape = CircleShape,
    border = BorderStroke(2.dp, (if (viewModel.kitColor.value == colorId) {
        DarkBlueOutlined
    } else Color.Transparent)),
    colors = CardDefaults.cardColors(getColorById(colorId))
) {}
}
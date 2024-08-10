package com.example.homemedicalkit.feature_medicine.presentation.navigation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.CreateNewFolder
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.homemedicalkit.R
import com.example.homemedicalkit.presentation.util.Screen
import com.example.homemedicalkit.ui.theme.Blue1
import com.example.homemedicalkit.ui.theme.LightBlue1

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val screens = listOf(
        listOf(Screen.KitsScreen.route, Icons.Filled.CreateNewFolder, R.string.kit),
        listOf(Screen.MedicinesList.route, Icons.Filled.FormatListNumbered, R.string.medicines),
        listOf(Screen.MedicineCard.route, Icons.Filled.AddCircleOutline, R.string.add)
    )
    var selectedItem by remember { mutableStateOf(0) }
    NavigationBar(
        modifier = Modifier
            .alpha(0.95f)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        containerColor = Blue1,
        tonalElevation = 10.dp
    ) {
        screens.forEach {item ->
            NavigationBarItem(
                icon = { Icon(item[1] as ImageVector, null) },
                label = {
                    Text(stringResource(item[2] as Int),
                        style = MaterialTheme.typography.bodySmall
                ) },
                selected = selectedItem == screens.indexOf(item),
                onClick = {
                    selectedItem = screens.indexOf(item)
                    navController.navigate(item[0].toString()) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(indicatorColor = LightBlue1)
            )
        }
    }
}
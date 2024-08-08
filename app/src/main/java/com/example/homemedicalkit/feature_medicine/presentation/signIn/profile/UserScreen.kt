package com.example.homemedicalkit.feature_medicine.presentation.signIn.profile
/* It was used to demonstrate sign in
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.homemedicalkit.featureMedicine.presentation.signIn.UserData
import com.example.homemedicalkit.presentation.kitsScreen.NavigationBarSample
import com.example.homemedicalkit.ui.theme.HomeMedicalKitTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserScreen(
    userData: UserData?,
    onSignOut: () -> Unit,
    navController: NavController,
) {
    HomeMedicalKitTheme {
        Scaffold(
            bottomBar = { NavigationBarSample(navController = navController, selectedItem = 3) },
            topBar = {
                TopAppBar(
                    title = { },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.navigateUp()
                            }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBackIosNew,
                                contentDescription = "Back"
                            )
                        }
                    },
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(100.dp))
                    if (userData?.profilePictureUrl != null) {
                        AsyncImage(
                            model = userData.profilePictureUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                    }
                    if (userData?.username != null) {
                        Text(
                            text = userData.username,
                            textAlign = TextAlign.Center,
                            fontSize = 30.sp,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    if (userData?.userEmail != null) {
                        Text(
                            text = userData.userEmail,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    if(userData?.userId != null)
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                        onClick = onSignOut) {
                        Text(text = "Выйти")
                    }
                    else{
                        Text(text = "Нет данных")
                    }
                }
            }
        )
    }
}

 */

package com.example.homemedicalkit.featureMedicine.presentation.signIn.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homemedicalkit.R
import com.example.homemedicalkit.featureMedicine.presentation.signIn.SignInState
import com.example.homemedicalkit.presentation.util.Screen
import com.example.homemedicalkit.ui.theme.HomeMedicalKitTheme

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit,
    navController: NavController
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    HomeMedicalKitTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp),
                painter = painterResource(id = R.drawable.first_aid_kit_bro),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                onClick = onSignInClick
            ) {
                Text(text = "Войти с помощью Google")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
                onClick = {
                    navController.navigate(
                        Screen.KitsScreen.route
                    )
                }) {
                Text(text = "Продолжить без регистрации")
            }
        }
    }
}
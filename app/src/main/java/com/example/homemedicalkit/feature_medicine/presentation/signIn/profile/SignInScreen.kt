package com.example.homemedicalkit.feature_medicine.presentation.signIn.profile

/* It was used to demonstrate sign in
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

 */
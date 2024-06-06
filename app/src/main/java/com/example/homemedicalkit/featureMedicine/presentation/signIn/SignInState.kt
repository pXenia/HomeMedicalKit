package com.example.homemedicalkit.featureMedicine.presentation.signIn

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
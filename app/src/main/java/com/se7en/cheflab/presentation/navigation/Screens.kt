package com.se7en.cheflab.presentation.screens

sealed class Screens(val route: String) {

    object OnBoardingScreen: Screens(route = "onboarding_screen")
    object LogInScreen: Screens(route = "login_screen")
    object SignUpScreen: Screens(route = "signup_screen")
    object HomeScreen: Screens(route = "home_screen")

}
package com.se7en.cheflab.presentation.home

data class SignInResult (
    val data: UserData?,
    val error: String?
)

data class UserData(
    val userId: String,
    val userName: String?,
    val profilePic: String?
)
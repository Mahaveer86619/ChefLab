package com.se7en.cheflab.presentation.home

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.se7en.cheflab.R
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class GoogleAuthUiClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {

    private val auth: FirebaseAuth = Firebase.auth

    suspend fun signIn(): IntentSender? {
        val result = try {

            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await() // coroutine waits till signed in

        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }

        return result?.pendingIntent?.intentSender //returns a intent with the user details but encoded

    }

    // decode the data with firebase's function itself, and SignIn
    suspend fun SignInWithIntent(intent: Intent): SignInResult {

        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)

        return try {

            val user = auth.signInWithCredential(googleCredentials).await().user
            SignInResult(
                data = user?.run{
                    UserData(
                        userId = uid,
                        userName = displayName,
                        profilePic = photoUrl?.toString()
                    )
                },
                error = null
            )

        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(data = null, error = e.message)
        }
    }

    // SignOut
    suspend fun SignOut() {
        try {

            oneTapClient.signOut().await()
            auth.signOut()

        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }


    // Get Current data of user
    fun getcurrentUserData(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            userName = displayName,
            profilePic = photoUrl?.toString()
        )
    }


    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false) // false shows the total list of google accounts
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true) // if only 1 acc no need to click, just log in
            .build()
    }

}
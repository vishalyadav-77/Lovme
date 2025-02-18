package com.love.lovme.GoogleSignIn

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.love.lovme.ChatViewModel
import com.love.lovme.R
import com.love.lovme.SignInResult
import com.love.lovme.UserData
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class GoogleAuthUiClient(private val context: Context, private val oneTapClient: SignInClient, val viewModel: ChatViewModel) {
    private val auth = Firebase.auth

    suspend fun signIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.builder().setGoogleIdTokenRequestOptions(
            GoogleIdTokenRequestOptions.builder().setSupported(true)
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId("395452617143-fv55k7ldg8hemfl85e3fp82kmh7fth1e.apps.googleusercontent.com")
                .build()
        ).setAutoSelectEnabled(true).build()
    }

    suspend fun signInWithIntent(intent: Intent): SignInResult {
        viewModel.resetState()
        val cred = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = cred.googleIdToken
        val googleCred = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCred).await().user
             SignInResult(
                 errorMessage = null,
                 data = user?.run {
                     UserData(
                         email = email.toString(),
                         userId = uid,
                         ppurl = photoUrl.toString().substring(0, photoUrl.toString().length-6)
                     )
                 }
             )

        }catch (e: Exception){
            e.printStackTrace()
            if(e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )

        }

    }


}

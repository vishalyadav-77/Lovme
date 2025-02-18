package com.love.lovme

import android.content.IntentSender
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.love.lovme.GoogleSignIn.GoogleAuthUiClient
import com.love.lovme.screens.SignInScreen
import com.love.lovme.ui.theme.LovmeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: ChatViewModel by viewModels()
    private val googleAuthUiClient by lazy{
        GoogleAuthUiClient(
            context = applicationContext,
            viewModel = viewModel,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = {
                    result->
                    if(result.resultCode== RESULT_OK){
                        lifecycleScope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                        }

                    }

                } )

            SignInScreen(onSignInClick = {
                lifecycleScope.launch {
                    val signInIntentSender= googleAuthUiClient.signIn()
                    launcher.launch(
                        IntentSenderRequest.Builder(
                            signInIntentSender ?: return@launch
                        ).build()
                    )
                }
            } )
        }

    }
}


@Composable
fun AppNavigation() {

}

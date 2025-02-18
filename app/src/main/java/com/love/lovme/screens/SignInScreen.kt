package com.love.lovme.screens

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.love.lovme.R
import com.love.lovme.ui.theme.mypink

@Preview(showSystemUi = true)
@Composable
fun PreviewSignInScreen(){
    val navController = rememberNavController()
    SignInScreen(onSignInClick = {  })
}

@Composable
fun SignInScreen(onSignInClick: () -> Unit){

    Image(painter = painterResource(id = R.drawable.bg),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(painter = painterResource(id = R.drawable.lovme_logo),
            contentDescription = "logo",
            modifier = Modifier
                .size(300.dp)
                .offset(y = (-70).dp)
        )
        Text(text = "Welcome to Lovme",
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.ExtraBold),
            color = Color.White
        )
        Text(text = "Long-distance never lasts? Challenge accepted.",
            color = Color.White
        )
        Spacer(modifier = Modifier.height(32.dp))



        Button(
            onClick = {
                      onSignInClick()

        }, colors = ButtonDefaults.buttonColors( containerColor = Color.White, // White button background
            contentColor = mypink),
            shape = RoundedCornerShape(3.dp)
            ) {
            Text("Sign in using Google", fontSize = 18.sp)
        }

    }
}


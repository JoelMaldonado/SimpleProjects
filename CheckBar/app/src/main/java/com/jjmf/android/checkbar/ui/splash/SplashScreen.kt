package com.jjmf.android.checkbar.ui.splash

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.android.checkbar.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    toLogin: () -> Unit,
    toMenu: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {


    LaunchedEffect(key1 = Unit) {
        viewModel.verificarSesion()
    }

    var targetValue by remember { mutableFloatStateOf(0f) }


    Text(text = targetValue.toString())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.height(targetValue.dp),
            contentScale = ContentScale.FillHeight
        )
    }


    val context = LocalContext.current as Activity
    context.window.statusBarColor = Color.Black.hashCode()
    LaunchedEffect(key1 = Unit) {
        repeat(100) {
            targetValue++
            delay(8)
        }

        if (viewModel.isSesionActiva) {
            toMenu()
        } else {
            toLogin()
        }
    }
}
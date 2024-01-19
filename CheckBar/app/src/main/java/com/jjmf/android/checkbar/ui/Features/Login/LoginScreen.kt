package com.jjmf.android.checkbar.ui.Features.Login

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.android.checkbar.R
import com.jjmf.android.checkbar.ui.Features.Login.components.LoginRecuerdame
import com.jjmf.android.checkbar.ui.components.CajaTexto
import com.jjmf.android.checkbar.ui.theme.ColorP1
import com.jjmf.android.checkbar.ui.theme.ColorTextos
import com.jjmf.android.checkbar.ui.theme.ColorTitulos

@Composable
fun LoginScreen(
    toMenu: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val focus = LocalFocusManager.current
    val isVisible = remember { mutableStateOf(false) }

    viewModel.error?.let {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        viewModel.error = null
    }

    if (viewModel.toMenu) {
        toMenu()
        viewModel.toMenu = false
    }


    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fondo_login),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_login),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Text(text = "Bienvenid@", color = ColorTextos)
            Text(text = "Inicia sesión", fontSize = 28.sp)
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CajaTexto(
                    valor = viewModel.user,
                    newValor = {
                        viewModel.errorUser = null
                        viewModel.user = it
                    },
                    label = "Usuario",
                    modifier = Modifier.fillMaxWidth(),
                    error = viewModel.errorUser,
                    imeAction = ImeAction.Next,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focus.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CajaTexto(
                        valor = viewModel.pass,
                        newValor = {
                            viewModel.errorPass = null
                            viewModel.pass = it
                        },
                        label = "Contraseña",
                        modifier = Modifier.fillMaxWidth(),
                        keyboardType = KeyboardType.Password,
                        error = viewModel.errorPass,
                        imeAction = ImeAction.Done,
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focus.clearFocus()
                                viewModel.login()
                            }
                        ),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    isVisible.value = !isVisible.value
                                }
                            ) {
                                val icon =
                                    if (isVisible.value) Icons.Default.Visibility else Icons.Default.VisibilityOff
                                Icon(imageVector = icon, contentDescription = null, tint = ColorP1)
                            }
                        },
                        visualTransformation = if (isVisible.value) VisualTransformation.None else PasswordVisualTransformation()
                    )
                    LoginRecuerdame(
                        modifier = Modifier.align(Alignment.End),
                        viewModel = viewModel
                    )
                }

                if (viewModel.loader) {
                    CircularProgressIndicator(
                        color = ColorP1,
                        modifier = Modifier.size(25.dp)
                    )
                } else {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(15.dp),
                        onClick = viewModel::login,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ColorP1,
                            contentColor = Color.White
                        ),
                        contentPadding = PaddingValues(vertical = 15.dp)
                    ) {
                        Text(text = "Ingresar")
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier
                        .padding(bottom = 10.dp),
                    text = "Version 1.0.0",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                /*
                Text(
                    text = "Recuperar Contraseña",
                    color = ColorTitulos,
                    fontWeight = FontWeight.Medium,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {

                    }
                )*/

            }
        }
    }

    val act = LocalContext.current as Activity
    act.window.statusBarColor = ColorP1.hashCode()
}
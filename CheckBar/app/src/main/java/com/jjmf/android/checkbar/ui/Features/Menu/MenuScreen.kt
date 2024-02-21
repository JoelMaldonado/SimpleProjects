package com.jjmf.android.checkbar.ui.Features.Menu

import android.app.Activity
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.android.checkbar.R
import com.jjmf.android.checkbar.app.BaseApp.Companion.prefs
import com.jjmf.android.checkbar.ui.components.CardMenu
import com.jjmf.android.checkbar.ui.components.TopBar
import com.jjmf.android.checkbar.ui.navigation.Rutas
import com.jjmf.android.checkbar.ui.theme.ColorP1

@Composable
fun MenuScreen(
    navHostController: NavHostController,
    viewModel: MenuViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        viewModel.getUser()
    }

    BackHandler {
        alertLogout(
            context = context,
            logout = {
                navHostController.popBackStack(Rutas.Login.url, inclusive = false)
            }
        )
    }

    TopBar(
        title = "¡Hola ${viewModel.nombre}!",
        trailing = {
            IconButton(
                onClick = {
                    alertLogout(
                        context = context,
                        logout = {
                            navHostController.popBackStack(Rutas.Login.url, inclusive = false)
                        }
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 30.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CardMenu(
                modifier = Modifier.fillMaxWidth(),
                icon = Icons.Default.ReceiptLong,
                text = "Inventarios",
                click = {
                    navHostController.navigate(Rutas.VerInventarios.url)
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CardMenu(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.Group,
                    text = "Usuarios",
                    click = {
                        navHostController.navigate(Rutas.VerUsuarios.url)
                    }
                )
                CardMenu(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.PersonAdd,
                    text = "Crear Usuario",
                    click = {
                        navHostController.navigate(Rutas.AddUsuario.url)
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CardMenu(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.Settings,
                    text = "Preferencias",
                    click = {
                        navHostController.navigate(Rutas.Preferencias.url)
                    }
                )
                CardMenu(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.AddCircle,
                    text = "Crear Inventario",
                    click = {
                        navHostController.navigate(Rutas.AddInventario.url)
                    }
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.img_menu),
                contentDescription = null,
                modifier = Modifier
                    .width(250.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.FillWidth
            )
        }
    }

    val act = LocalContext.current as Activity
    act.window.statusBarColor = ColorP1.hashCode()
}

fun alertLogout(context: Context, logout: () -> Unit) {
    SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).apply {
        titleText = "¿Desea salir de la aplicación?"
        contentText = "Se cerrará la sesión actual"
        setConfirmButton("Salir") {
            it.dismissWithAnimation()
            prefs.deleteIDUser()
            logout()
        }
        setCancelButton("Cancelar") {
            it.dismissWithAnimation()
            it.cancel()
        }
        show()
    }
}

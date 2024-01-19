package com.jjmf.android.checkbar.ui.Features.AddUsuario

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.android.checkbar.ui.Features.AddUsuario.Screens.HomeAddUsuarioScreen
import com.jjmf.android.checkbar.ui.Features.AddUsuario.Screens.SelectAreaScreen
import com.jjmf.android.checkbar.ui.components.TopBar
import com.jjmf.android.checkbar.util.show

@Composable
fun AddUsuarioScreen(
    id: String?,
    back: () -> Unit,
    viewModel: AddUsuarioViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit){
        viewModel.init(id)
    }

    // Retroceder
    if (viewModel.back) {
        back()
        context.show(viewModel.mensaje)
        viewModel.back = false
        viewModel.mensaje = null
    }

    val nav = rememberNavController()

    TopBar(
        leading = {
            IconButton(onClick = back) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        title = if (id === null) "Agregar Usuario" else "Editar Usuario ${viewModel.usuario}",
        trailing = {
            if (id != null) {
                IconButton(
                    onClick = {
                        SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("¿Desea eliminar?")
                            .setContentText("Se eliminará este usuario, ¿Desea continuar?")
                            .setConfirmText("Si")
                            .setConfirmClickListener {
                                it.dismissWithAnimation()
                                viewModel.delete(id)
                            }
                            .setCancelButton("No") {
                                it.dismissWithAnimation()
                            }
                            .show()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.DeleteSweep,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    ) {


        NavHost(
            navController = nav,
            startDestination = "home",
            modifier = Modifier.fillMaxSize()
        ) {
            composable(
                route = "home",
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -1000 },
                        animationSpec = tween(300)
                    )
                },
                popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { -1000 },
                        animationSpec = tween(300)
                    )
                }
            ) {
                HomeAddUsuarioScreen(
                    id = id,
                    toSelectArea = {
                        nav.navigate("areas")
                    },
                    viewModel = viewModel
                )
            }
            composable(
                route = "areas",
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { 1000 },
                        animationSpec = tween(300)
                    )
                },
                popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { 1000 },
                        animationSpec = tween(300)
                    )
                }
            ) {
                SelectAreaScreen(
                    //listAreas = viewModel.state.listAreas,
                    back = {
                        nav.popBackStack()
                    },
                    viewModel = viewModel
                )
            }
        }
    }

}
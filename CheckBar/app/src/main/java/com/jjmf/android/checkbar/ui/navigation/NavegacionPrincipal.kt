package com.jjmf.android.checkbar.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjmf.android.checkbar.ui.Features.AddInventario.AddInventarioScreen
import com.jjmf.android.checkbar.ui.Features.AddUsuario.AddUsuarioScreen
import com.jjmf.android.checkbar.ui.Features.Login.LoginScreen
import com.jjmf.android.checkbar.ui.Features.Menu.MenuScreen
import com.jjmf.android.checkbar.ui.Features.Preferencias.PreferenciasScreen
import com.jjmf.android.checkbar.ui.Features.VerInventarios.VerInventariosScreen
import com.jjmf.android.checkbar.ui.Features.VerUsuarios.VerUsuariosScreen
import com.jjmf.android.checkbar.ui.splash.SplashScreen

@Composable
fun NavegacionPrincipal() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Rutas.Splash.url) {

        composable(Rutas.Splash.url){
            SplashScreen(
                toLogin = {
                    navController.navigate(Rutas.Login.url)
                }
            )
        }

        composable(Rutas.Login.url) {
            LoginScreen(
                toMenu = {
                    navController.navigate(Rutas.Menu.url)
                }
            )
        }
        composable(Rutas.Menu.url) {
            MenuScreen(
                navHostController = navController
            )
        }
        composable(Rutas.Preferencias.url) {
            PreferenciasScreen()
        }
        composable(Rutas.VerInventarios.url) {
            VerInventariosScreen(
                back = {
                    navController.popBackStack()
                },
                addInventario = {
                    navController.navigate(Rutas.AddInventario.url)
                }
            )
        }
        composable(Rutas.AddInventario.url) {
            AddInventarioScreen(
                back = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = Rutas.VerUsuarios.url
        ) {
            VerUsuariosScreen(
                toAddUsuario = {
                    if (it == null) navController.navigate(Rutas.AddUsuario.url)
                    else navController.navigate(Rutas.AddUsuario.sendId(it))
                }
            )
        }
        composable(
            route = Rutas.AddUsuario.url,
        ) {
            val id = it.arguments?.getString("id")
            AddUsuarioScreen(
                id = id,
                back = {
                    navController.popBackStack()
                }
            )
        }
    }
}
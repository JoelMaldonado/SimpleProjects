package com.jjmf.android.checkbar.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Rutas(val url: String) {
    object Splash : Rutas("splash")
    object Login : Rutas("login")
    object Menu : Rutas("menu")
    object Preferencias : Rutas("preferencias")
    object VerInventarios : Rutas("verinventarios")
    object AddInventario : Rutas("addinventario")
    object VerUsuarios : Rutas("verusuarios")
    object AddUsuario : Rutas("addusuario?{id}") {
        fun sendId(id: String?) = "addusuario?$id"
    }
}
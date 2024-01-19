package com.jjmf.android.checkbar.domain.model

data class Usuario(
    val id: String,
    val usuario: String,
    val clave: String,
    val nombre: String,
    val apellido: String,
    val area: String,
    val rol: Rol,
    val fotoUrl: String,
)

enum class Rol (val code: String, val descrip: String){
    ADMIN("A", "Admin"),
    USER("U", "User"),
    SINROL("S", "Sin Rol")
}

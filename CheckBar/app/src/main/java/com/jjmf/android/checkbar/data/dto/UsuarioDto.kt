package com.jjmf.android.checkbar.data.dto

import com.google.firebase.firestore.Exclude
import com.jjmf.android.checkbar.domain.model.Rol
import com.jjmf.android.checkbar.domain.model.Usuario

data class UsuarioDto(
    @get:Exclude var id: String? = null,
    var fotoUrl: String? = null,
    val usuario: String? = null,
    val clave: String? = null,
    val nombre: String? = null,
    val apellido: String? = null,
    var area: String? = null,
    val rol: String? = null
){
    fun toDomain(): Usuario{
        return Usuario(
            id = id ?: "Sin Valor",
            usuario = usuario ?: "Sin Valor",
            clave = clave ?: "Sin Valor",
            nombre = nombre ?: "Sin Valor",
            apellido = apellido ?: "Sin Valor",
            area = area ?: "Sin Valor",
            rol = Rol.values().firstOrNull{it.code == rol} ?: Rol.SINROL,
            fotoUrl = fotoUrl ?: "Sin Valor"
        )
    }
}

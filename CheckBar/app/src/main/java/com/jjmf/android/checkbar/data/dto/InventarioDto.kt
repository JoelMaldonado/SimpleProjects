package com.jjmf.android.checkbar.data.dto

import com.google.firebase.firestore.Exclude
import com.jjmf.android.checkbar.domain.model.Inventario

class InventarioDto(
    @get:Exclude var id: String? = null,
    val foto: String? = null,
    val nombre: String? = null,
    val cant: Int? = null
){
    fun toDomain(): Inventario {
        return Inventario(
            id = id ?: "",
            foto = foto ?: "",
            nombre = nombre ?: "",
            cant = cant ?: 0
        )
    }
}
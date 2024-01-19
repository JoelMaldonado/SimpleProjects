package com.jjmf.android.checkbar.data.dto

import com.google.firebase.firestore.Exclude
import com.jjmf.android.checkbar.domain.model.Area

data class AreaDto(
    @get:Exclude var id : String? = null,
    val nombre : String? = null,
    val activo: Boolean? = null
){
    fun toDomain() = Area(
        id = id ?: "Sin Valor",
        nombre = nombre ?: "Sin Valor",
        activo = activo ?: false
    )
}

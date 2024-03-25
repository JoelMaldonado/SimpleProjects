package com.jjmf.android.checkbar.data.dto

import com.google.firebase.firestore.Exclude
import com.jjmf.android.checkbar.domain.model.Categoria

data class CategoriaDto(
    @get:Exclude var id:String? = null,
    val nombre:String? = null
) {
    fun toDomain(): Categoria {
        return Categoria(
            id = id,
            nombre = nombre ?: "Sin Valor"
        )
    }
}
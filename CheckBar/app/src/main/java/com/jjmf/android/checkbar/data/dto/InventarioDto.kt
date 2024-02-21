package com.jjmf.android.checkbar.data.dto

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude
import com.jjmf.android.checkbar.domain.model.Inventario


enum class TipoMovimiento {
    Ingreso,
    Salida
}

data class InventarioDto(
    @get:Exclude var id: String? = null,
    val foto: String? = null,
    val nombre: String? = null,
    var movimientos: List<MovimientoDto>? = null,
    var fecha: Timestamp? = null
) {
    fun toDomain(): Inventario {
        return Inventario(
            id = id ?: "",
            foto = foto ?: "",
            nombre = nombre ?: "",
            movimientos = movimientos?.map { it.toDomain() } ?: emptyList(),
            fecha = fecha ?: Timestamp.now()
        )
    }
}
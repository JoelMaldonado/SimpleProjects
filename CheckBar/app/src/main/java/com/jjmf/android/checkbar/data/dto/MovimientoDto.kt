package com.jjmf.android.checkbar.data.dto

import com.google.firebase.Timestamp
import com.jjmf.android.checkbar.domain.model.Movimiento


data class MovimientoDto(
    var cant: Int? = null,
    var tipo: TipoMovimiento? = null,
    var fecha: Timestamp? = null
) {
    fun toDomain() : Movimiento{
        return Movimiento(
            cant = cant ?: 0,
            tipo = tipo ?: TipoMovimiento.Ingreso,
            fecha = fecha ?: Timestamp.now()
        )
    }
}
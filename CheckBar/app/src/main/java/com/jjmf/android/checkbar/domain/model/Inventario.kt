package com.jjmf.android.checkbar.domain.model

import com.google.firebase.Timestamp
import com.jjmf.android.checkbar.data.dto.TipoMovimiento


data class Inventario(
    val id: String,
    val foto: String,
    val nombre: String,
    val movimientos: List<Movimiento>,
    val fecha:Timestamp
) {
    fun getTotal(): Int {
        val ingresos = movimientos.filter { it.tipo == TipoMovimiento.Ingreso }.sumOf { it.cant }
        val salidas = movimientos.filter { it.tipo == TipoMovimiento.Salida }.sumOf { it.cant }
        return ingresos - salidas
    }
}
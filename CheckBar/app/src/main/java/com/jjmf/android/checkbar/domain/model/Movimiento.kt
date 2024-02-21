package com.jjmf.android.checkbar.domain.model

import com.google.firebase.Timestamp
import com.jjmf.android.checkbar.data.dto.TipoMovimiento

data class Movimiento(
    val cant: Int,
    val tipo: TipoMovimiento,
    val fecha:Timestamp
)

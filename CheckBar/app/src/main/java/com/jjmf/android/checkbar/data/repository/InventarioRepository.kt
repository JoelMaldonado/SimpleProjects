package com.jjmf.android.checkbar.data.repository

import com.jjmf.android.checkbar.Core.EResult
import com.jjmf.android.checkbar.data.dto.InventarioDto
import com.jjmf.android.checkbar.data.dto.MovimientoDto
import com.jjmf.android.checkbar.domain.model.Inventario
import kotlinx.coroutines.flow.Flow

interface InventarioRepository {

    suspend fun getAll(): EResult<List<Inventario>>
    suspend fun getAllFlow() : Flow<EResult<List<Inventario>>>
    suspend fun insert(inventario: InventarioDto): EResult<String>
    suspend fun update(inv: InventarioDto) : EResult<String>
    suspend fun insertMov(id:String, mov: MovimientoDto): Result<Boolean>
    suspend fun get(id: String) : Result<Inventario>
    suspend fun delete(id: String) : EResult<String>

}
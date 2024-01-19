package com.jjmf.android.checkbar.data.repository

import com.jjmf.android.checkbar.Core.EResult
import com.jjmf.android.checkbar.data.dto.UsuarioDto
import com.jjmf.android.checkbar.domain.model.Usuario
import kotlinx.coroutines.flow.Flow

interface UsuarioRepository {

    suspend fun login(user: String, pass: String): EResult<String>
    suspend fun getAll(): EResult<List<Usuario>>
    suspend fun getAllFlow(): Flow<EResult<List<Usuario>>>
    suspend fun addUsuario(usuarioDto: UsuarioDto): EResult<String>
    suspend fun ifExist(field: String, valor: Any?) : Boolean
    suspend fun get(id: String): Usuario?
    suspend fun delete(id: String) : EResult<String>
}
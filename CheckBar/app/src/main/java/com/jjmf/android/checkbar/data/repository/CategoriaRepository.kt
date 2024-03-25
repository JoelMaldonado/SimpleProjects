package com.jjmf.android.checkbar.data.repository

import com.jjmf.android.checkbar.Core.EResult
import com.jjmf.android.checkbar.domain.model.Categoria

interface CategoriaRepository {

    suspend fun getAll(): EResult<List<Categoria>>
    suspend fun insert(nombre: String) : EResult<Boolean>

}
package com.jjmf.android.checkbar.data.repository

import com.jjmf.android.checkbar.data.dto.AreaDto
import com.jjmf.android.checkbar.domain.model.Area

interface AreaRepository {
    suspend fun getAll(): List<Area>
    suspend fun ifExist(field: String, valor: Any?) : Boolean
    suspend fun add(areaDto: AreaDto) : String
}
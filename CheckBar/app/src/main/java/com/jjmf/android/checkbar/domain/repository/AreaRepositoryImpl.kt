package com.jjmf.android.checkbar.domain.repository

import com.google.firebase.firestore.CollectionReference
import com.jjmf.android.checkbar.Core.EResult
import com.jjmf.android.checkbar.data.dto.AreaDto
import com.jjmf.android.checkbar.data.module.FirebaseModule
import com.jjmf.android.checkbar.data.repository.AreaRepository
import com.jjmf.android.checkbar.domain.model.Area
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AreaRepositoryImpl @Inject constructor(
    @FirebaseModule.AreaCollection private val fb: CollectionReference
) : AreaRepository{
    override suspend fun getAll(): List<Area> {
        val snapshot = fb.get().await()
        val list = mutableListOf<Area>()
        for (document in snapshot.documents) {
            val area = document.toObject(AreaDto::class.java)
            if (area != null) {
                area.id = document.id
                list.add(area.toDomain())
            }
        }
        return list
    }

    override suspend fun ifExist(field: String, valor: Any?): Boolean {
        val querySnapshot = fb.whereEqualTo(field,valor).get().await()
        return !querySnapshot.isEmpty
    }

    override suspend fun add(areaDto: AreaDto) : EResult<Boolean> {
        return try {
            val documentReference = fb.add(areaDto).await()
            return EResult.Success(true)
        } catch (e: Exception) {
            EResult.Error(e.message.toString())
        }
    }
}
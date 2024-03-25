package com.jjmf.android.checkbar.domain.repository

import com.google.firebase.firestore.CollectionReference
import com.jjmf.android.checkbar.Core.EResult
import com.jjmf.android.checkbar.data.dto.CategoriaDto
import com.jjmf.android.checkbar.data.module.FirebaseModule
import com.jjmf.android.checkbar.data.repository.CategoriaRepository
import com.jjmf.android.checkbar.domain.model.Categoria
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CategoriaRepositoryImpl @Inject constructor(
    @FirebaseModule.CategoriaCollection private val fb: CollectionReference
) : CategoriaRepository {
    override suspend fun getAll(): EResult<List<Categoria>> {
        return try {
            val querySnapshot = fb.get().await()
            val categorias = mutableListOf<Categoria>()
            for (document in querySnapshot.documents) {
                val categoria = document.toObject(CategoriaDto::class.java)
                categoria?.let { categorias.add(it.toDomain()) }
            }
            EResult.Success(categorias)
        }catch (e:Exception){
            EResult.Error(e.message.toString())
        }
    }

    override suspend fun insert(nombre: String): EResult<Boolean> {
        return try {
            val categoria = CategoriaDto(nombre = nombre)
            fb.add(categoria).await()
            EResult.Success(true)
        } catch (e: Exception) {
            EResult.Error(e.message.toString())
        }
    }

}
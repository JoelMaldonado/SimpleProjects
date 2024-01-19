package com.jjmf.android.checkbar.domain.repository

import com.google.firebase.firestore.CollectionReference
import com.jjmf.android.checkbar.Core.EResult
import com.jjmf.android.checkbar.data.dto.InventarioDto
import com.jjmf.android.checkbar.data.module.FirebaseModule
import com.jjmf.android.checkbar.data.repository.InventarioRepository
import com.jjmf.android.checkbar.domain.model.Inventario
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class InventarioRepositoryImpl @Inject constructor(
    @FirebaseModule.InventarioCollection private val fb: CollectionReference
) : InventarioRepository {
    override suspend fun getAll(): EResult<List<Inventario>> {
        return try {
            val inventario = fb.get().await().toObjects(InventarioDto::class.java)
            EResult.Success(inventario.map { it.toDomain() })
        }catch (e:Exception){
            EResult.Error(e.message.toString())
        }
    }

    override suspend fun getAllFlow(): Flow<EResult<List<Inventario>>> = callbackFlow{
        val listener = fb.addSnapshotListener { value, error ->
            if (error != null) {
                trySend(EResult.Error(error.message.toString()))
            } else {
                val list = mutableListOf<Inventario>()
                value?.forEach {
                    val usuarioDto = it.toObject(InventarioDto::class.java)
                    usuarioDto.id = it.id
                    list.add(usuarioDto.toDomain())
                }
                trySend(EResult.Success(list))
            }
        }
        awaitClose { listener.remove() }
    }

    override suspend fun insert(inventario: InventarioDto): EResult<String> {
        return try {
            fb.add(inventario).await()
            EResult.Success("Inventario agregado correctamente")
        }catch (e:Exception){
            EResult.Error(e.message.toString())
        }
    }

    override suspend fun update(inv: InventarioDto): EResult<String> {
        return try {
            inv.id?.let { fb.document(it).set(inv).await() }
            EResult.Success("Inventario actualizado correctamente")
        }catch (e:Exception){
            EResult.Error(e.message.toString())
        }
    }

}
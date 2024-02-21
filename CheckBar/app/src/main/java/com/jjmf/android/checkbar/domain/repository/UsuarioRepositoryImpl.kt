package com.jjmf.android.checkbar.domain.repository

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.jjmf.android.checkbar.Core.EResult
import com.jjmf.android.checkbar.data.dto.UsuarioDto
import com.jjmf.android.checkbar.data.module.FirebaseModule
import com.jjmf.android.checkbar.data.repository.UsuarioRepository
import com.jjmf.android.checkbar.domain.model.Usuario
import com.jjmf.android.checkbar.domain.usecase.AddUserUsecase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsuarioRepositoryImpl @Inject constructor(
    @FirebaseModule.UsuarioCollection private val fb: CollectionReference,
) : UsuarioRepository {

    override suspend fun login(user: String, pass: String): EResult<String> {
        return try {
            val querySnapshot =
                fb.whereEqualTo("usuario", user).whereEqualTo("clave", pass).get().await()
            if (querySnapshot.documents.isEmpty()) {
                EResult.Error("Usuario o contraseña incorrectos")
            } else {
                EResult.Success(querySnapshot.documents[0].id)
            }
        } catch (e: Exception) {
            EResult.Error(e.message.toString())
        }
    }

    override suspend fun getAll(): EResult<List<Usuario>> {
        return try {
            val res = fb.get().await()
            val list = mutableListOf<Usuario>()
            res.forEach {
                val usuarioDto = it.toObject(UsuarioDto::class.java)
                usuarioDto.id = it.id
                list.add(usuarioDto.toDomain())
            }
            EResult.Success(list)
        } catch (e: Exception) {
            EResult.Error(e.message.toString())
        }
    }

    override suspend fun getAllFlow(): Flow<EResult<List<Usuario>>> = callbackFlow {
        val listener = fb.addSnapshotListener { value, error ->
            if (error != null) {
                trySend(EResult.Error(error.message.toString()))
            } else {
                val list = mutableListOf<Usuario>()
                value?.forEach {
                    val usuarioDto = it.toObject(UsuarioDto::class.java)
                    usuarioDto.id = it.id
                    list.add(usuarioDto.toDomain())
                }
                trySend(EResult.Success(list))
            }
        }
        awaitClose { listener.remove() }
    }

    override suspend fun addUsuario(usuarioDto: UsuarioDto): EResult<String> {
        return try {
            Log.d("tagitoAdd", usuarioDto.toString())
            if (usuarioDto.id != null) {
                fb.document(usuarioDto.id!!).set(usuarioDto).await()
                EResult.Success("Usuario actualizado correctamente")
            } else {
                fb.add(usuarioDto).await()
                EResult.Success("Usuario agregado correctamente")
            }
        } catch (e: Exception) {
            EResult.Error(e.message.toString())
        }
    }

    override suspend fun ifExist(field: String, valor: Any?): Boolean {
        val querySnapshot = fb.whereEqualTo(field, valor).get().await()
        return !querySnapshot.isEmpty
    }

    override suspend fun get(id: String?): Usuario? {
        return try {
            val res = fb.document(id.toString()).get().await()
            val usuarioDto = res.toObject(UsuarioDto::class.java)
            usuarioDto?.id = res.id
            usuarioDto?.toDomain()
        } catch (e: Exception) {
            Log.d("tagito", e.message.toString())
            null
        }
    }

    override suspend fun delete(id: String): EResult<String> {
        return try {
            fb.document(id).delete().await()
            EResult.Success("Usuario eliminado correctamente")
        } catch (e: Exception) {
            EResult.Error(e.message.toString())
        }
    }
}
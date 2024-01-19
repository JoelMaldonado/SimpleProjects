package com.jjmf.android.checkbar.domain.usecase

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.jjmf.android.checkbar.Core.EResult
import com.jjmf.android.checkbar.data.dto.AreaDto
import com.jjmf.android.checkbar.data.dto.UsuarioDto
import com.jjmf.android.checkbar.data.repository.AreaRepository
import com.jjmf.android.checkbar.data.repository.UsuarioRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AddUserUsecase @Inject constructor(
    private val repository: UsuarioRepository,
    private val repArea: AreaRepository,
    private val comprimirFotoUseCase: ComprimirFotoUseCase,
) {

    suspend operator fun invoke(
        usuarioDto: UsuarioDto,
        fotoUrl: Uri?,
    ): AddUserResult {

        //Verificamos que los campos no esten vacios
        if (usuarioDto.usuario?.isEmpty() == true) {
            return AddUserResult.ErrorUsuario("El usuario no puede estar vacio")
        }

        //Verificamos que el usuario no exista
        if (
            usuarioDto.id == null &&
            repository.ifExist("usuario", usuarioDto.usuario?.uppercase())
        ) {
            return AddUserResult.ErrorUsuario("El usuario ya existe")
        }

        //Verificamos que el nombre no este vacio
        if (usuarioDto.nombre?.isEmpty() == true) {
            return AddUserResult.ErrorNombre("El nombre no puede estar vacio")
        }

        //Verificamos que el apellido no este vacio
        if (usuarioDto.apellido?.isEmpty() == true) {
            return AddUserResult.ErrorApellido("El apellido no puede estar vacio")
        }

        //Verificamos que el area no este vacio
        if (usuarioDto.area?.isEmpty() == true) {
            return AddUserResult.ErrorArea("El area no puede estar vacia")
        }

        //Verificamos que la foto no este vacia y la comprimimos para subirla a firebase
        if (fotoUrl != null && fotoUrl.toString().contains("content")) {
            comprimirFotoUseCase(fotoUrl)?.let {
                val fb = FirebaseStorage
                    .getInstance()
                    .reference
                    .child("FotosPerfil")
                    .child("${usuarioDto.usuario}.jpg")
                usuarioDto.fotoUrl = fb.putFile(it).await().storage.downloadUrl.await().toString()
            }
        }

        //Verificamos que el area exista y si no la agregamos
        if (!repArea.ifExist("nombre", usuarioDto.area)) {
            repArea.add(AreaDto(nombre = usuarioDto.area, activo = true))
        }

        //Agregamos el usuario a firebase
        return when (val res = repository.addUsuario(usuarioDto)) {
            is EResult.Error -> AddUserResult.Error(res.mensajeError)
            is EResult.Success -> AddUserResult.Success
        }
    }

    sealed class AddUserResult {
        object Success : AddUserResult()
        data class ErrorUsuario(val error: String) : AddUserResult()
        data class ErrorNombre(val error: String) : AddUserResult()
        data class ErrorApellido(val error: String) : AddUserResult()
        data class ErrorArea(val error: String) : AddUserResult()
        data class Error(val error: String) : AddUserResult()
    }
}
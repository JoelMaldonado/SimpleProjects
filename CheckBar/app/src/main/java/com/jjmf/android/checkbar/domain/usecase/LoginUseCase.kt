package com.jjmf.android.checkbar.domain.usecase

import com.jjmf.android.checkbar.Core.EResult
import com.jjmf.android.checkbar.data.repository.UsuarioRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UsuarioRepository
) {

    suspend operator fun invoke(user: String, pass: String): LoginResult {
        if (user.isEmpty()) {
            return LoginResult.ErrorUsuario("El usuario no puede estar vacio")
        }

        if (pass.isEmpty()) {
            return LoginResult.ErrorPass("La contraseña no puede estar vacia")
        }

        return repository.login(user, pass).let {
            when (it) {
                is EResult.Success -> LoginResult.Success(it.data)
                is EResult.Error -> LoginResult.Error(it.mensajeError)
            }
        }
    }

    sealed class LoginResult {
        data class Success(val id:String) : LoginResult()
        data class ErrorUsuario(val error: String) : LoginResult()
        data class ErrorPass(val error: String) : LoginResult()
        data class Error(val error: String) : LoginResult()
    }
}
package com.jjmf.android.checkbar.ui.Features.AddUsuario

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.checkbar.data.dto.UsuarioDto
import com.jjmf.android.checkbar.data.repository.AreaRepository
import com.jjmf.android.checkbar.data.repository.UsuarioRepository
import com.jjmf.android.checkbar.domain.model.Rol
import com.jjmf.android.checkbar.domain.usecase.AddUserUsecase
import com.jjmf.android.checkbar.domain.usecase.AddUserUsecase.AddUserResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUsuarioViewModel @Inject constructor(
    private val addUserUsecase: AddUserUsecase,
    private val repository: UsuarioRepository,
    private val repoArea: AreaRepository,
) : ViewModel() {

    var uri by mutableStateOf<Uri?>(null)
    var usuario by mutableStateOf("")
    var errorUsuario by mutableStateOf<String?>(null)
    var nombre by mutableStateOf("")
    var errorNombre by mutableStateOf<String?>(null)
    var apellido by mutableStateOf("")
    var errorApellido by mutableStateOf<String?>(null)
    var area by mutableStateOf("")
    var errorArea by mutableStateOf<String?>(null)
    var rol by mutableStateOf(Rol.USER)
    var loader by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)
    var back by mutableStateOf(false)
    var mensaje by mutableStateOf<String?>(null)
    var listAreas by mutableStateOf<List<String>>(emptyList())

    fun init(id: String?) {
        if (id != null) {
            getUsuario(id)
        }
        listarAreas()
    }

    fun addEditUsuario(id: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loader = true
                val usuarioDto = UsuarioDto(
                    id = id,
                    usuario = usuario.uppercase(),
                    clave = usuario,
                    nombre = nombre,
                    apellido = apellido,
                    area = area,
                    rol = rol.code
                )
                val res = addUserUsecase(
                    usuarioDto = usuarioDto,
                    fotoUrl = uri
                )
                when (res) {
                    is AddUserResult.Error -> error = res.error
                    is AddUserResult.ErrorApellido -> errorApellido = res.error
                    is AddUserResult.ErrorArea -> errorArea = res.error
                    is AddUserResult.ErrorNombre -> errorNombre = res.error
                    is AddUserResult.ErrorUsuario -> errorUsuario = res.error
                    AddUserResult.Success -> {
                        back = true
                        mensaje = "Usuario guardado"
                    }
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                loader = false
            }
        }
    }

    fun delete(id: String) {
        viewModelScope.launch {
            try {
                loader = true
                repository.delete(id)
                back = true
                mensaje = "Usuario eliminado correctamente"
            } catch (e: Exception) {
                error = e.message
            } finally {
                loader = false
            }
        }
    }

    private fun getUsuario(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loader = true
                val user = repository.get(id)
                user?.let {
                    usuario = it.usuario
                    nombre = it.nombre
                    apellido = it.apellido
                    area = it.area
                    rol = it.rol
                    uri = it.fotoUrl.toUri()
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                loader = false
            }
        }
    }

    private fun listarAreas() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                listAreas = repoArea.getAll().filter { it.activo }.map { it.nombre }
            } catch (e: Exception) {
                error = e.message
            }
        }
    }
}
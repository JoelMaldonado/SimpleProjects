package com.jjmf.android.checkbar.ui.Features.VerUsuarios

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.checkbar.data.repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.jjmf.android.checkbar.Core.EResult
import com.jjmf.android.checkbar.domain.model.Usuario
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerUsuariosViewModel @Inject constructor(
    private val repository: UsuarioRepository,
) : ViewModel() {

    var loader by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)
    var listUsuarios by mutableStateOf<List<Usuario>>(emptyList())

    fun getUsuarios() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loader = true
                repository.getAllFlow().collect { res ->
                    when (res) {
                        is EResult.Error -> error = res.mensajeError
                        is EResult.Success -> {
                            listUsuarios = res.data
                        }
                    }
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                loader = false
            }
        }
    }

}
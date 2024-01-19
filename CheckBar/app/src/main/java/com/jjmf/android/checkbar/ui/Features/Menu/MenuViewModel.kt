package com.jjmf.android.checkbar.ui.Features.Menu

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.checkbar.Core.EResult
import com.jjmf.android.checkbar.app.BaseApp.Companion.prefs
import com.jjmf.android.checkbar.data.repository.InventarioRepository
import com.jjmf.android.checkbar.data.repository.UsuarioRepository
import com.jjmf.android.checkbar.domain.model.Inventario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val repository: InventarioRepository,
    private val repUser: UsuarioRepository,
) : ViewModel() {

    var loader by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    var nombre by mutableStateOf("")

    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loader = true
                val res = repUser.get(prefs.getIdUser())
                nombre = res?.nombre ?: "Sin nombre"
            } catch (e: Exception) {
                error = e.message
            } finally {
                loader = false
            }
        }
    }

}
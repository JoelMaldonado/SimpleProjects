package com.jjmf.android.checkbar.ui.Features.AddInventario

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.checkbar.Core.EResult
import com.jjmf.android.checkbar.data.dto.InventarioDto
import com.jjmf.android.checkbar.data.repository.InventarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddInventarioViewModel @Inject constructor(
    private val repository: InventarioRepository,
) : ViewModel() {

    var foto by mutableStateOf<Uri?>(null)
    var detalle by mutableStateOf("")
    var cant by mutableStateOf("")

    var loader by mutableStateOf(false)
    var back by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun insert() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loader = true
                val inventario = InventarioDto(
                    foto = foto.toString(),
                    nombre = detalle,
                    cant = cant.toInt()
                )
                when (val res = repository.insert(inventario)) {
                    is EResult.Success -> back = true
                    is EResult.Error -> error = res.mensajeError
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                loader = false
            }
        }
    }
}
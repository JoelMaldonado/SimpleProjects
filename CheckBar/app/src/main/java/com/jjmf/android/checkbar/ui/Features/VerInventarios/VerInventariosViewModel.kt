package com.jjmf.android.checkbar.ui.Features.VerInventarios

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.checkbar.Core.EResult
import com.jjmf.android.checkbar.data.dto.InventarioDto
import com.jjmf.android.checkbar.data.repository.InventarioRepository
import com.jjmf.android.checkbar.domain.model.Inventario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerInventariosViewModel @Inject constructor(
    private val repository: InventarioRepository,
) : ViewModel() {


    var dialogEditInventario by mutableStateOf<Inventario?>(null)
    var loader by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)
    var listInventario by mutableStateOf<List<Inventario>>(emptyList())

    fun getListInventario() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loader = true
                repository.getAllFlow().collect{res->
                    when (res) {
                        is EResult.Success -> {
                            listInventario = res.data
                            loader = false
                        }
                        is EResult.Error -> {
                            error = res.mensajeError
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

    var loaderAlert by mutableStateOf(false)

    fun editInventario(inv: InventarioDto) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loaderAlert = true
                when(val res = repository.update(inv)){
                    is EResult.Error -> error = res.mensajeError
                    is EResult.Success -> {
                        dialogEditInventario = null
                    }
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                loaderAlert = false
            }
        }
    }

}
package com.jjmf.android.checkbar.ui.Features.VerInventarios

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.checkbar.Core.EResult
import com.jjmf.android.checkbar.data.dto.MovimientoDto
import com.jjmf.android.checkbar.data.repository.InventarioRepository
import com.jjmf.android.checkbar.domain.model.Inventario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerInventariosViewModel @Inject constructor(
    private val repository: InventarioRepository
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

    fun editInventario(id:String, mov: MovimientoDto) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loaderAlert = true
                val res = repository.insertMov(id, mov)
                if (res.isSuccess){
                    dialogEditInventario = null
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                loaderAlert = false
            }
        }
    }

}
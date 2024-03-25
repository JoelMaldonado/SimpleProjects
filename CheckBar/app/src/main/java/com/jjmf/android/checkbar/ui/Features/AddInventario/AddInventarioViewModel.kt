package com.jjmf.android.checkbar.ui.Features.AddInventario

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.jjmf.android.checkbar.Core.EResult
import com.jjmf.android.checkbar.data.dto.AreaDto
import com.jjmf.android.checkbar.data.dto.InventarioDto
import com.jjmf.android.checkbar.data.dto.MovimientoDto
import com.jjmf.android.checkbar.data.dto.TipoMovimiento
import com.jjmf.android.checkbar.data.repository.AreaRepository
import com.jjmf.android.checkbar.data.repository.CategoriaRepository
import com.jjmf.android.checkbar.data.repository.InventarioRepository
import com.jjmf.android.checkbar.domain.model.Area
import com.jjmf.android.checkbar.domain.model.Categoria
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddInventarioViewModel @Inject constructor(
    private val repository: InventarioRepository,
    private val categoriaRepository: CategoriaRepository,
    private val areaRepository: AreaRepository
) : ViewModel() {

    var alertAddCategoria by mutableStateOf(false)

    var alertAddArea by mutableStateOf(false)

    var foto by mutableStateOf<Uri?>(null)
    var detalle by mutableStateOf("")
    var cant by mutableStateOf("")
    var categoria by mutableStateOf("")
    var area by mutableStateOf("")

    var loader by mutableStateOf(false)
    var back by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    var listCategorias by mutableStateOf<List<Categoria>>(emptyList())
    var listAreas by mutableStateOf<List<Area>>(emptyList())

    fun getCategorias() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = categoriaRepository.getAll()
                when (res) {
                    is EResult.Error -> error = res.mensajeError
                    is EResult.Success -> listCategorias = res.data
                }
            } catch (e: Exception) {
                error = e.message
            }
        }
    }
    fun getAreas() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                listAreas = areaRepository.getAll()
            } catch (e: Exception) {
                error = e.message
            }
        }
    }

    fun insert() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loader = true
                val inventario = InventarioDto(
                    foto = foto.toString(),
                    nombre = detalle,
                    movimientos = listOf(
                        MovimientoDto(
                            cant = cant.toIntOrNull(),
                            tipo = TipoMovimiento.Ingreso,
                            fecha = Timestamp.now()
                        )
                    ),
                    categoria = categoria,
                    area = area,
                    fecha = Timestamp.now()
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

    var isLoadingAlertAddCategoria by mutableStateOf(false)
    var errorAlertAddCategoria by mutableStateOf<String?>(null)

    fun addCategoria(nombre: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoadingAlertAddCategoria = true
                val res  = categoriaRepository.insert(nombre)
                when(res){
                    is EResult.Error -> errorAlertAddCategoria = res.mensajeError
                    is EResult.Success -> {
                        alertAddCategoria = false
                        getCategorias()
                        categoria = nombre
                    }
                }
            } catch (e: Exception) {
                errorAlertAddCategoria = e.message
            }finally {
                isLoadingAlertAddCategoria = false
            }
        }
    }

    var isLoadingAlertAddArea by mutableStateOf(false)
    var errorAlertAddArea by mutableStateOf<String?>(null)

    fun addArea(nombre: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoadingAlertAddArea = true
                when(val res  = areaRepository.add(AreaDto(nombre = nombre))){
                    is EResult.Error -> errorAlertAddArea = res.mensajeError
                    is EResult.Success -> {
                        alertAddArea = false
                        getAreas()
                        area = nombre
                    }
                }
            } catch (e: Exception) {
                errorAlertAddArea = e.message
            }finally {
                isLoadingAlertAddArea = false
            }
        }
    }
}
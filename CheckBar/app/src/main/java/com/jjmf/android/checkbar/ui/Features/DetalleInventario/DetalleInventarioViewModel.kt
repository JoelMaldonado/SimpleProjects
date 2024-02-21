package com.jjmf.android.checkbar.ui.Features.DetalleInventario

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.checkbar.data.repository.InventarioRepository
import com.jjmf.android.checkbar.domain.model.Inventario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetalleInventarioViewModel @Inject constructor(
    private val repository: InventarioRepository
) : ViewModel() {

    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)
    var inventario by mutableStateOf<Inventario?>(null)

    fun get(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                val res = repository.get(id)
                if (res.isSuccess) {
                    inventario = res.getOrNull()
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }

}
package com.jjmf.android.checkbar.ui.splash

import android.util.Log
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.checkbar.app.BaseApp.Companion.prefs
import com.jjmf.android.checkbar.data.repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: UsuarioRepository
) : ViewModel() {

    var isSesionActiva by mutableStateOf(false)
    fun verificarSesion() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val usuario = repository.get(prefs.getIdUser())
                if (usuario != null) {
                    isSesionActiva = true
                }
            } catch (e: Exception) {
                Log.d("tagito", e.message.toString())
            }
        }
    }

}
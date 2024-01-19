package com.jjmf.android.checkbar.ui.Features.Login

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.checkbar.Core.EResult
import com.jjmf.android.checkbar.app.BaseApp.Companion.prefs
import com.jjmf.android.checkbar.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var recuerdame by mutableStateOf(prefs.getRecuerdame())
    var user by mutableStateOf(prefs.getUsuario())
    var errorUser by mutableStateOf<String?>(null)
    var pass by mutableStateOf("")
    var errorPass by mutableStateOf<String?>(null)

    var loader by mutableStateOf(false)
    var toMenu by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loader = true
                when(val res = loginUseCase(user, pass)) {
                    is LoginUseCase.LoginResult.ErrorUsuario -> errorUser = res.error
                    is LoginUseCase.LoginResult.ErrorPass -> errorPass = res.error
                    is LoginUseCase.LoginResult.Error -> error = res.error
                    is LoginUseCase.LoginResult.Success -> {
                        toMenu = true
                        prefs.saveIdUser(res.id)
                        prefs.saveRecuerdame(recuerdame)
                        if(recuerdame){
                            prefs.saveUsuario(user)
                        }else{
                            prefs.saveUsuario("")
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
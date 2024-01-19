package com.jjmf.android.checkbar.Core

sealed class EResult<out T> {
    data class Success<out T>(val data: T) : EResult<T>()
    data class Error(val mensajeError: String) : EResult<Nothing>()
}

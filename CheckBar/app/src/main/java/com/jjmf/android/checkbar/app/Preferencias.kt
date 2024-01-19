package com.jjmf.android.checkbar.app

import android.content.Context

class Preferencias(context: Context) {

    private val SHARED_NAME = "MYDTB"
    private val storage = context.getSharedPreferences(SHARED_NAME, 0)

    private val KEY_USUARIO = "KEY_FECHA"
    private val KEY_IDUSER = "KEY_HORA"
    private val KEY_RECUERDAME = "KEY_RUC"

    fun saveUsuario(valor: String) = storage.edit().putString(KEY_USUARIO, valor).apply()
    fun getUsuario() = storage.getString(KEY_USUARIO, "") ?: ""

    fun saveIdUser(valor: String) = storage.edit().putString(KEY_IDUSER, valor).apply()
    fun getIdUser() = storage.getString(KEY_IDUSER, "") ?: ""

    fun saveRecuerdame(valor: Boolean) = storage.edit().putBoolean(KEY_RECUERDAME, valor).apply()
    fun getRecuerdame() = storage.getBoolean(KEY_RECUERDAME, false)
}
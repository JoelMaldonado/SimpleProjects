package com.jjmf.android.checkbar.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application() {

    companion object {
        lateinit var prefs: Preferencias
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Preferencias(applicationContext)
    }
}
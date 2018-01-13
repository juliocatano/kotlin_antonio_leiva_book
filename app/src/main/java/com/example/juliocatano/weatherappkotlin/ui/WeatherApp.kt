package com.example.juliocatano.weatherappkotlin.ui

import android.app.Application
import com.example.juliocatano.weatherappkotlin.ui.utils.DelegateExt

class WeatherApp : Application() {

    companion object {
        var instance: WeatherApp by DelegateExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
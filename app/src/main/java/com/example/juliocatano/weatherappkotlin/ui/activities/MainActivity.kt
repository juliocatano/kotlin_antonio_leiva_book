package com.example.juliocatano.weatherappkotlin.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.juliocatano.weatherappkotlin.R
import com.example.juliocatano.weatherappkotlin.domain.commands.RequestForecastCommand
import com.example.juliocatano.weatherappkotlin.ui.adapters.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        forecastList.layoutManager = LinearLayoutManager(this);

        doAsync {
            val result = RequestForecastCommand("050031").execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result) { forecast -> toast(forecast.date) }
            }
        }
    }
}

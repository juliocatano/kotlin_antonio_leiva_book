package com.example.juliocatano.weatherappkotlin.data.mappers

import com.example.juliocatano.weatherappkotlin.data.model.CityForecast
import com.example.juliocatano.weatherappkotlin.data.model.DayForecast
import com.example.juliocatano.weatherappkotlin.domain.entities.Forecast
import com.example.juliocatano.weatherappkotlin.domain.entities.ForecastList

class DbDataMapper {
    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertToDomain(it) }
        ForecastList(_id, city, country, daily)
    }

    private fun convertToDomain(forecast: DayForecast) = with(forecast) {
        Forecast(date, description, high, low, iconUrl)
    }
}
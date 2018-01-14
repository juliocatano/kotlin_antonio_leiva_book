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

    fun convertFromDomain(forecast: ForecastList) = with(forecast) {
        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id, city, country, daily)
    }

    private fun convertDayFromDomain(cityId: Long, forecast: Forecast) = with(forecast) {
        DayForecast(date, description, high, low, iconUrl,  cityId)
    }
}
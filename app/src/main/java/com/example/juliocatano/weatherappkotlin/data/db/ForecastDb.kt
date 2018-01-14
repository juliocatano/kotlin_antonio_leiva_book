package com.example.juliocatano.weatherappkotlin.data.db

import com.example.juliocatano.weatherappkotlin.data.mappers.DbDataMapper
import com.example.juliocatano.weatherappkotlin.data.model.CityForecast
import com.example.juliocatano.weatherappkotlin.data.model.DayForecast
import com.example.juliocatano.weatherappkotlin.domain.entities.ForecastList
import com.example.juliocatano.weatherappkotlin.extensions.clear
import com.example.juliocatano.weatherappkotlin.extensions.parseList
import com.example.juliocatano.weatherappkotlin.extensions.parseOpt
import com.example.juliocatano.weatherappkotlin.extensions.toVarArgArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class ForecastDb(
        private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
        private val dataMapper: DbDataMapper = DbDataMapper()) {

    fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.NAME} = ? AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { DayForecast(HashMap(it)) }
        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }

        if (city != null) dataMapper.convertToDomain(city) else null
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarArgArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarArgArray())
            }
        }
    }
}

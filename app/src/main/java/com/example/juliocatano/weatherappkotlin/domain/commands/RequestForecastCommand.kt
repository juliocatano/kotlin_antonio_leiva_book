package com.example.juliocatano.weatherappkotlin.domain.commands

import com.example.juliocatano.weatherappkotlin.data.mappers.ForecastDataMapper
import com.example.juliocatano.weatherappkotlin.data.service.ForecastRequest
import com.example.juliocatano.weatherappkotlin.domain.entities.ForecastList

class RequestForecastCommand(private val zipCode: String): Command<ForecastList>{
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.run())
    }
}

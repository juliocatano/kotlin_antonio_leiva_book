package com.example.juliocatano.weatherappkotlin.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.juliocatano.weatherappkotlin.ui.WeatherApp
import org.jetbrains.anko.db.*

class ForecastDbHelper(ctx: Context = WeatherApp.instance) : ManagedSQLiteOpenHelper(
        ctx,
        ForecastDbHelper.DB_NAME,
        null,
        ForecastDbHelper.DB_VERSION) {

    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance by lazy { ForecastDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            with(db) {
                createTable(CityForecastTable.NAME,
                        true,
                        CityForecastTable.ID to INTEGER + PRIMARY_KEY,
                        CityForecastTable.CITY to TEXT,
                        CityForecastTable.COUNTRY to TEXT)

                createTable(DayForecastTable.NAME,
                        true,
                        DayForecastTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                        DayForecastTable.DATE to INTEGER,
                        DayForecastTable.DESCRIPTION to TEXT,
                        DayForecastTable.HIGH to INTEGER,
                        DayForecastTable.LOW to INTEGER,
                        DayForecastTable.ICON_URL to TEXT,
                        DayForecastTable.CITY_ID to INTEGER)
            }
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.dropTable(CityForecastTable.NAME, true)
            db.dropTable(DayForecastTable.NAME, true)
        }
        onCreate(db)
    }
}
package com.example.juliocatano.weatherappkotlin.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.juliocatano.weatherappkotlin.R
import com.example.juliocatano.weatherappkotlin.domain.entities.Forecast
import com.example.juliocatano.weatherappkotlin.domain.entities.ForecastList
import com.example.juliocatano.weatherappkotlin.ui.utils.ctx
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*

class ForecastListAdapter(private val weakForecastList: ForecastList,
                          private val itemClick: (Forecast) -> Unit)
    : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater
                .from(parent.ctx)
                .inflate(R.layout.item_forecast, parent, false),
                itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bindForecast(weakForecastList[position])

    override fun getItemCount(): Int = weakForecastList.size

    class ViewHolder(view: View, private val itemClick: (Forecast) -> Unit)
        : RecyclerView.ViewHolder(view) {

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = date
                itemView.description.text = description
                itemView.maxTemperature.text = "$high"
                itemView.minTemperature.text = "$low"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}
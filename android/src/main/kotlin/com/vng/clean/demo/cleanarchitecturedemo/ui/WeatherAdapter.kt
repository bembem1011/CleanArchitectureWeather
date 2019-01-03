package com.vng.clean.demo.cleanarchitecturedemo.ui

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vng.clean.demo.cleanarchitecturedemo.GlideApp
import com.vng.clean.demo.cleanarchitecturedemo.R
import com.vng.clean.demo.cleanarchitecturedemo.model.WeatherInfoModel
import kotlinx.android.synthetic.main.layout_weather_item.view.*

class WeatherAdapter: ListAdapter<WeatherInfoModel, WeatherAdapter.WeatherViewHolder>(diffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder = WeatherViewHolder(parent)

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class WeatherViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_weather_item, parent, false)) {
        var weatherInfoModel: WeatherInfoModel? = null

        fun bindTo(item: WeatherInfoModel?) {
            weatherInfoModel = item
            weatherInfoModel?.let {
                GlideApp.with(itemView.context)
                        .load(it.iconUrl)
                        .placeholder(R.color.gray)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(itemView.iconImageView)

                itemView.descriptionTextView.text = it.description
                itemView.temperature.text = it.tempreature.toString()
            }
        }
    }

    companion object {
        private val diffCallBack = object: DiffUtil.ItemCallback<WeatherInfoModel>() {
            override fun areItemsTheSame(oldItem: WeatherInfoModel, newItem: WeatherInfoModel): Boolean {
                return oldItem?.dtime == newItem?.dtime
            }

            override fun areContentsTheSame(oldItem: WeatherInfoModel, newItem: WeatherInfoModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
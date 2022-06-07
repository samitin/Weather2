package ru.samitin.weather.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    val city: City = getDefaultCity(),
    val weatherToDay : WeatherToDay?,
    val weatherFact: WeatherFact?,
    val weatherForecast: WeatherForecast?,
    val weatherPart : List<WeatherPart>?
):Parcelable


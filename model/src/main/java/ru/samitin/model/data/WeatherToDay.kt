package ru.samitin.weather.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherToDay(
    val city: City,
    val date: String?,
    val icon: String?,
    val temperature: Int?,
    val condition: String?,
    val temperature_min_max: String?,
    val feels_like: Int?,
    //сдесь будет массив со списком на весь день
): Parcelable

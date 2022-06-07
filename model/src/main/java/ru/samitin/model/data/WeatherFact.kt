package ru.samitin.weather.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherFact(
    val windSpeed: Float?,  //Скорость ветра (в м/с).
    val windGust: Float?,   //Скорость порывов ветра (в м/с).
    val windDir: String? ,  //Направление ветра
    val pressureMM: Float? ,//Давление (в мм рт. ст.).
    val pressurePA: Float?, //Давление (в гектопаскалях).
    val humidity: Float?,    //Влажность воздуха (в процентах).
    val daytime: String?,    //	Светлое или темное время суток. Возможные значения: «d» — светлое время суток.«n» — темное время суток.
    val season: String?,     //Время года в данном населенном пункте
):Parcelable

package ru.samitin.weather.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherForecast (
    val date: String?,        //Дата прогноза в формате ГГГГ-ММ-ДД.
    val week: Int?,           //Порядковый номер недели.
    val sunrise: String?,     //Время восхода Солнца, локальное время (может отсутствовать для полярных регионов).
    val sunset: String?,      //Время заката Солнца, локальное время (может отсутствовать для полярных регионов).
    val moon_code: Int?,      //Код фазы Луны
    val moon_text: String?,   //Текстовый код для фазы Луны
        ): Parcelable
package ru.samitin.retrofit.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import ru.samitin.weather.model.dto.WeatherDTO

interface WeatherAPI {
    @GET("v2/informers")
    fun getWeather(
        @Header("X-Yandex-API-Key") token: String,
        @Query("lat")lat: Double,
        @Query("lon")lon: Double,
        //@Query("lang")lang: String
    ): Deferred<WeatherDTO>
}
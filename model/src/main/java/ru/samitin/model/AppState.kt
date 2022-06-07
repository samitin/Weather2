package ru.samitin.weather.model

import ru.samitin.weather.model.data.City
import ru.samitin.weather.model.data.Weather


sealed class AppState {
    data class Success(val weatherData: Weather?) : AppState()
    data class SuccessFromDB(val citesData: List<City>?): AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}

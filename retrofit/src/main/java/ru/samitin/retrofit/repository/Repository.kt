package ru.samitin.retrofit.repository

import ru.samitin.weather.model.data.City

interface Repository <T> {

    suspend fun getData(city: City): T
}
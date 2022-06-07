package ru.samitin.retrofit.dataSource

import ru.samitin.weather.model.data.City

interface DataSource<T> {
    suspend fun getData(city: City) : T
}
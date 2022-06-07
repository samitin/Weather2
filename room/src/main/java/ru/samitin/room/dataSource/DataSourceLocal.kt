package ru.samitin.room.dataSource

import ru.samitin.weather.model.AppState

interface DataSourceLocal <T>  {
    suspend fun saveToDB(data : T)
    suspend fun getAllData() : List<T>
}
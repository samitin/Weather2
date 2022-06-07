package ru.samitin.core.viewModel

import ru.samitin.weather.model.data.City

interface Interactor<T> {

    suspend fun getData(city : City, fromRemoteSource: Boolean): T

    suspend fun saveData(city: City)

    suspend fun getDataListCity():List<City>
}
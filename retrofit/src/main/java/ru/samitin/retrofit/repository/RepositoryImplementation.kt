package ru.samitin.retrofit.repository

import ru.samitin.retrofit.dataSource.DataSource
import ru.samitin.utils.convertDtoToModel
import ru.samitin.weather.model.data.City
import ru.samitin.weather.model.data.Weather
import ru.samitin.weather.model.dto.WeatherDTO


class RepositoryImplementation(private val dataSource: DataSource<WeatherDTO>) :
    Repository<Weather> {

    override suspend fun getData(city : City): Weather {
        city
        return convertDtoToModel(city,dataSource.getData(city))
    }
}
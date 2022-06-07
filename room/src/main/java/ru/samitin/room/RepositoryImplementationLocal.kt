package ru.samitin.room

import ru.samitin.room.dataSource.DataSourceLocal
import ru.samitin.room.dataSource.RoomDataBaseImplementation
import ru.samitin.room.dataSource.data.HistoryEntity
import ru.samitin.room.utils.convertHistoryEntityToCity
import ru.samitin.weather.model.data.City


class RepositoryImplementationLocal(
    private val dataSource: DataSourceLocal<HistoryEntity> )
    : RepositoryLocal<City> {
    override suspend fun getData(): List<City> {
        val arrayCity = mutableListOf<City>()
        dataSource.getAllData().map {
            arrayCity.add(convertHistoryEntityToCity(it))
        }
        return arrayCity
    }

    override suspend fun saveToDB(data: City) {

    }


}
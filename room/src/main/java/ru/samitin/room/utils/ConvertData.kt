package ru.samitin.room.utils

import ru.samitin.room.dataSource.data.HistoryEntity
import ru.samitin.weather.model.data.City

fun convertHistoryEntityToCity(historyEntity: HistoryEntity):City{
    return City(historyEntity.city,historyEntity.lat,historyEntity.lon)
}

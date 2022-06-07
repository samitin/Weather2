package ru.samitin.room.dataSource

import ru.samitin.room.dataSource.data.HistoryEntity
import ru.samitin.room.room.HistoryDao

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<HistoryEntity> {
    override suspend fun saveToDB(data: HistoryEntity) {
        historyDao.insert(data)
    }

    override suspend fun getAllData(): List<HistoryEntity> {
        return historyDao.all()
    }


}
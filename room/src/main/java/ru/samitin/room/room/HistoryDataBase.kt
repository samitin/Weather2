package ru.samitin.room.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.samitin.room.dataSource.data.HistoryEntity

@Database(entities = arrayOf(HistoryEntity::class),version = 1,exportSchema = false)
abstract class HistoryDataBase : RoomDatabase(){
    abstract fun historyDao(): HistoryDao
}
package ru.samitin.room.room

import android.database.Cursor
import androidx.room.*
import ru.samitin.room.dataSource.data.HistoryEntity

@Dao
interface HistoryDao {

    @Query("SELECT * FROM HistoryEntity")
    fun all(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE city LIKE :city")
    fun getDataByCityName(city: String): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: HistoryEntity)

    @Update
    fun update(entity: HistoryEntity)

    @Delete
    fun delete(entity: HistoryEntity)

    @Query("DELETE FROM HistoryEntity WHERE id = :id")
    fun deleteById(id: Long)

    @Query("SELECT id, city FROM HistoryEntity")
    fun getHistoryCursor():Cursor

    @Query("SELECT id, city FROM HistoryEntity WHERE id = :id")
    fun getHistoryCursor(id: Long): Cursor
}
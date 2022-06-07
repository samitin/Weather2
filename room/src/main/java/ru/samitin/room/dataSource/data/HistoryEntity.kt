package ru.samitin.room.dataSource.data

import androidx.room.Entity
import androidx.room.PrimaryKey

const val ID = "id"
const val CITY = "city"
const val TEMPERATURE = "temperature"
@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0,
    var city:String = "",
    var lat:Double = 0.0,
    var lon:Double = 0.0
)

package ru.samitin.weather.model.dto

data class FactDto(val temp:Int?,           //Температура (°C)
                   val feels_like:Int?,     //Ощущаемая температура (°C).
                   val condition:String?,   //Погодное описание
                   val icon:String?,        //Код иконки погоды. Иконка доступна по адресу https://yastatic.net/weather/i/icons/funky/dark/<значение из поля icon>.svg.
                   val wind_speed: Float?,  //Скорость ветра (в м/с).
                   val wind_gust: Float?,   //Скорость порывов ветра (в м/с).
                   val wind_dir: String? ,  //Направление ветра
                   val pressure_mm: Float? ,//Давление (в мм рт. ст.).
                   val pressure_pa: Float?, //Давление (в гектопаскалях).
                   val humidity: Float?,    //Влажность воздуха (в процентах).
                   val daytime: String?,    //	Светлое или темное время суток. Возможные значения: «d» — светлое время суток.«n» — темное время суток.
                   val polar: Boolean?,     //Признак того, что время суток, указанное в поле daytime, является полярным.
                   val season: String?,     //Время года в данном населенном пункте
                   val obs_time: Int? )     //Время замера погодных данных в формате Unixtime.


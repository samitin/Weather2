package ru.samitin.weather.model.dto

data class PartDTO (
    val part_name: String?,  //Название времени суток
    val temp_min: Int?,      //Минимальная температура для времени суток (°C).
    val temp_max: Int?,      //Максимальная температура для времени суток (°C).
    val temp_avg: Int?,      //Средняя температура для времени суток (°C).
    val feels_like: Int?,    //Ощущаемая температура (°C).
    val icon: String?,       //	Код иконки погоды
    val condition: String?,  //Код расшифровки погодного описания
    val daytime: String?,    //Светлое или темное время суток. Возможные значения: «d» — светлое время суток.«n» — темное время суток.
    val polar: Boolean?,     //Признак того, что время суток, указанное в поле daytime, является полярным.
    val wind_speed: Float?,  //Скорость ветра (в м/с).
    val wind_gust: Float?,   //Скорость порывов ветра (в м/с).
    val wind_dir: String?,   //Направление ветра.
    val pressure_mm: Float?, //Давление (в мм рт. ст.).
    val pressure_pa: Float?, //Давление (в гектопаскалях)
    val humidity: Float?,    //Влажность воздуха (в процентах).
    val prec_mm: Float?,     //Прогнозируемое количество осадков (в мм).
    val prec_period: Int?,   //Прогнозируемый период осадков (в минутах).
    val prec_prob: Int?      //	Вероятность выпадения осадков.
)

package ru.samitin.weather.model.dto

data class ForecastDTO (
    val date: String?,        //Дата прогноза в формате ГГГГ-ММ-ДД.
    val date_ts: Int?,        //Дата прогноза в формате Unixtime.
    val week: Int?,           //Порядковый номер недели.
    val sunrise: String?,     //Время восхода Солнца, локальное время (может отсутствовать для полярных регионов).
    val sunset: String?,      //Время заката Солнца, локальное время (может отсутствовать для полярных регионов).
    val moon_code: Int?,      //Код фазы Луны
    val moon_text: String?,   //Текстовый код для фазы Луны
    val parts: List<PartDTO>? //Прогнозы по времени суток
)


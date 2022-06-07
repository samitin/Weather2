package ru.samitin.utils


import ru.samitin.weather.model.data.*
import ru.samitin.weather.model.dto.WeatherDTO


fun convertDtoToModel(city: City,weatherDTO: WeatherDTO): Weather {
    return Weather(getDefaultCity(),
        weatherDTO.convertWeatherDtoToWeatherToDay(city),
        weatherDTO.convertToWeatherFact(),
        weatherDTO.convertToWeatherForecast(),
        weatherDTO.convertToWeatherPart())
}



fun WeatherDTO.convertWeatherDtoToWeatherToDay(city:City): WeatherToDay{
    return WeatherToDay(
        city = city,
        date = forecast?.date,
        icon = fact?.icon,
        temperature = fact?.temp,
        condition = fact?.condition,
        temperature_min_max = "${forecast?.parts?.get(0)?.temp_min}°/${forecast?.parts?.get(0)?.temp_max}°",
        feels_like = fact?.feels_like)
}
fun WeatherDTO.convertToWeatherFact():WeatherFact{
    return WeatherFact(
        fact?.wind_speed,
        fact?.wind_gust,
        fact?.wind_dir,
        fact?.pressure_mm,
        fact?.pressure_pa,
        fact?.humidity,
        fact?.daytime,
        fact?.season
    )
}
fun WeatherDTO.convertToWeatherForecast():WeatherForecast{
    return WeatherForecast(
        forecast?.date,
        forecast?.week,
        forecast?.sunrise,
        forecast?.sunset,
        forecast?.moon_code,
        forecast?.moon_text
    )
}
fun WeatherDTO.convertToWeatherPart():List<WeatherPart> {
    val parts = mutableListOf<WeatherPart>()
    for (it in forecast?.parts!!) {
        parts.add(
            WeatherPart(
                it.part_name,
                it.temp_min,
                it.temp_max,
                it.temp_avg,
                it.feels_like,
                it.icon,
                it.condition,
                it.daytime,
                it.polar,
                it.wind_speed,
                it.wind_gust,
                it.wind_dir,
                it.pressure_mm,
                it.pressure_pa,
                it.humidity,
                it.prec_mm,
                it.prec_period,
                it.prec_prob
            )
        )

    }
    return parts
}
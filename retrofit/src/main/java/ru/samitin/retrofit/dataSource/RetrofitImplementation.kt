package ru.samitin.retrofit.dataSource

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.samitin.retrofit.api.BaseInterceptor
import ru.samitin.retrofit.api.WeatherAPI


import ru.samitin.weather.model.data.City

import ru.samitin.weather.model.dto.WeatherDTO


class RetrofitImplementation(private val apiKey : String) : DataSource<WeatherDTO> {

    override suspend fun getData(city: City): WeatherDTO {
        val weather = getService(BaseInterceptor.interceptor).getWeather(apiKey,lat = city.lat, lon =city.lon).await()
        return weather
    }

    private fun getService(interceptor: Interceptor): WeatherAPI {
        val retrofit = createRetrofit(interceptor)
        return retrofit.create(WeatherAPI::class.java)
    }

    private fun createRetrofit(interceptor: Interceptor): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(createOkHttpClient(interceptor))
            .build()
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    companion object {
        private const val BASE_URL_LOCATIONS = "https://api.weather.yandex.ru/"
    }

}

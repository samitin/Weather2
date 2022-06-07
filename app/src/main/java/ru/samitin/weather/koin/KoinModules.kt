package ru.samitin.weather.koin

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.samitin.retrofit.dataSource.RetrofitImplementation
import ru.samitin.retrofit.repository.Repository
import ru.samitin.retrofit.repository.RepositoryImplementation
import ru.samitin.room.RepositoryImplementationLocal
import ru.samitin.room.RepositoryLocal
import ru.samitin.room.dataSource.RoomDataBaseImplementation
import ru.samitin.room.room.HistoryDataBase
import ru.samitin.weather.BuildConfig
import ru.samitin.weather.model.data.City
import ru.samitin.weather.model.data.Weather
import ru.samitin.weather.view.main.MainInteractor
import ru.samitin.weather.viewmodel.MainViewModel


// Для удобства создадим две переменные: в одной находятся зависимости,
// используемые во всём приложении, во второй - зависимости конкретного экрана
val application = module {
// Функция single сообщает Koin, что эта зависимость должна храниться
// в виде синглтона (в Dagger есть похожая аннотация)
// Аннотация named выполняет аналогичную Dagger функцию
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }

    single<Repository<Weather>> {
       RepositoryImplementation(
            dataSource = RetrofitImplementation(
                BuildConfig.WEATHER_API_KEY
            )
        )
    }

    single<RepositoryLocal<City>> {
        RepositoryImplementationLocal(
            dataSource = RoomDataBaseImplementation(
                historyDao = get()
            )
        )
    }
}
// Функция factory сообщает Koin, что эту зависимость нужно создавать каждый
// раз заново, что как раз подходит для Activity и её компонентов.
val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    viewModel { MainViewModel(get()) }
}
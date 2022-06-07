package ru.samitin.weather.view.main

import ru.samitin.core.viewModel.Interactor
import ru.samitin.retrofit.repository.Repository
import ru.samitin.room.RepositoryLocal
import ru.samitin.weather.model.AppState
import ru.samitin.weather.model.data.City
import ru.samitin.weather.model.data.Weather


class MainInteractor(
    private val repositoryRemote: Repository<Weather>,
    private val repositoryLocal: RepositoryLocal<City>
    ) : Interactor<AppState> {

        override suspend fun getData(city: City, fromRemoteSource: Boolean): AppState {
            val appState: AppState
            if (fromRemoteSource) {
                val weather = repositoryRemote.getData(city = city)
                appState = AppState.Success(weather)
                repositoryLocal.saveToDB(weather.city)
            } else {
                appState = AppState.Error(Throwable("нет сети интернета!"))
            }
            return appState
        }

    override suspend fun saveData(city: City) {
        if (city != null){
            repositoryLocal?.saveToDB(city)
        }
    }

    override suspend fun getDataListCity(): List<City> {
        return repositoryLocal.getData()
    }
}

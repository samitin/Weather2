package ru.samitin.weather.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.samitin.core.viewModel.BaseViewModel
import ru.samitin.weather.model.data.City
import ru.samitin.weather.model.AppState
import ru.samitin.weather.view.main.MainInteractor

class MainViewModel(private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(city : City, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(city, isOnline) }
    }


    //Doesn't have to use withContext for Retrofit call if you use .addCallAdapterFactory(CoroutineCallAdapterFactory()). The same goes for Room
    private suspend fun startInteractor(city: City, isOnline: Boolean) = withContext(Dispatchers.IO) {
        _mutableLiveData.postValue(interactor.getData(city, isOnline))
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)//TODO Workaround. Set View to original state
        super.onCleared()
    }

    override  fun getDataFromDB() {
        viewModelCoroutineScope.launch {
            withContext(Dispatchers.IO){
                _mutableLiveData.postValue(AppState.SuccessFromDB(interactor.getDataListCity()))
            }
        }

    }
}
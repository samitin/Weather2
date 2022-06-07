package ru.samitin.core.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.samitin.weather.model.data.City
import ru.samitin.weather.model.AppState

abstract class BaseViewModel<T: AppState>(
    protected open val _mutableLiveData: MutableLiveData<T> = MutableLiveData(),
) : ViewModel(){

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }
    protected fun cancelJob(){
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }
    abstract fun getData(city : City,isOnline:Boolean)
    abstract  fun getDataFromDB()
    abstract fun handleError(error: Throwable)
}
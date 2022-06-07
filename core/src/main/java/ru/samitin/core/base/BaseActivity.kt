package ru.samitin.core.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.samitin.core.R
import ru.samitin.core.databinding.LoadingLayoutBinding
import ru.samitin.core.viewModel.BaseViewModel
import ru.samitin.core.viewModel.Interactor

import ru.samitin.weather.model.AppState
import ru.samitin.weather.model.data.Weather
import ru.samitin.weather.utils.network.isOnline


private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"

abstract class BaseActivity<T : AppState, I : Interactor<T>> : AppCompatActivity() {

    private lateinit var binding: LoadingLayoutBinding
    abstract val model: BaseViewModel<T>
    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isNetworkAvailable = isOnline(applicationContext)
        binding = LoadingLayoutBinding.inflate(layoutInflater)
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline(applicationContext)
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    protected fun renderData(appState: T) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                appState.weatherData?.let {
                    if (it == null) {
                        showAlertDialog(
                            getString(R.string.dialog_tittle_sorry),
                            getString(R.string.empty_server_response_on_success)
                        )
                    } else {
                        setData(it)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState != null) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_stub), appState.error.message)
            }
        }
    }

    protected fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    protected fun showAlertDialog(title: String?, message: String?) {
       // AlertDialogFragment.newInstance(title, message).show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun showViewWorking() {
        binding.loadingFrameLayout.visibility = View.GONE
    }

    private fun showViewLoading() {
        binding.loadingFrameLayout.visibility = View.VISIBLE
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    abstract fun setData(data: Weather)
}
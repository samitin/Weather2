package ru.samitin.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.samitin.weather.databinding.FragmentWeatherBinding
import ru.samitin.weather.model.data.Weather

class WeatherFragment :Fragment(){

    private lateinit var _binding: FragmentWeatherBinding
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWeatherBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weather = arguments?.getParcelable<Weather>(WEATHER_KEY) as Weather
        renderData(weather)
    }


    fun renderData(weather: Weather) {
                addFragment(WeatherToDayFragment.newInstance(weather.weatherToDay!!))
                addFragment(WeatherFactFragment.newInstance(weather.weatherFact!!))
                addFragment(WeatherForecastFragment.newInstance(weather.weatherForecast!!))
                for (part in weather?.weatherPart!!)
                    addFragment(WeatherPartFragment.newInstance(part))

    }
    fun addFragment(fragment: Fragment){
        childFragmentManager.beginTransaction()
            .add(binding.container.id,fragment)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null!!
    }
    companion object {
        private const val WEATHER_KEY = "WEATHER_KEY"
        fun newInstance(weather: Weather) =
            WeatherFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(WEATHER_KEY, weather)
                }
            }
    }
}
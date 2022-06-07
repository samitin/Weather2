package ru.samitin.weather.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.samitin.weather.databinding.FragmentWeahterForecastBinding
import ru.samitin.weather.model.data.WeatherForecast

class WeatherForecastFragment : Fragment() {

    private lateinit var weatherForecast: WeatherForecast
    private lateinit var _binding: FragmentWeahterForecastBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeahterForecastBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        weatherForecast = arguments?.getParcelable<WeatherForecast>(WEATHER_FORECAST_KEY) as WeatherForecast
        renderData(weatherForecast)
    }

    private fun renderData(weatherForecast: WeatherForecast) {
        weatherForecast.apply {
            binding.date.text = date
            binding.week.text = week.toString()
            binding.sunrise.text = sunrise
            binding.sunset.text = sunset
            binding.moonText.text = moon_text
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null!!
    }
    companion object {
        private const val WEATHER_FORECAST_KEY = "WEATHER_FORECAST_KEY"
        fun newInstance(forecast: WeatherForecast) : WeatherForecastFragment{
            val fragment = WeatherForecastFragment()
            val bundle = Bundle()
            bundle.putParcelable(WEATHER_FORECAST_KEY,forecast)
            fragment.arguments = bundle
            return fragment
        }

    }

}
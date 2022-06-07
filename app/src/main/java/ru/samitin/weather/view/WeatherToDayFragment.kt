package ru.samitin.weather.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import ru.samitin.weather.databinding.WeatherToDayFragmentBinding
import ru.samitin.weather.model.data.WeatherToDay
import ru.samitin.weather.utils.network.loadUrl


private const val WEATHER_TO_DAY_KEY = "WEATHER_TO_DAY_KEY"
class WeatherToDayFragment : Fragment() {

    private lateinit var weatherToDay: WeatherToDay
    private lateinit var _binding: WeatherToDayFragmentBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = WeatherToDayFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherToDay = arguments?.getParcelable<WeatherToDay>(WEATHER_TO_DAY_KEY) as WeatherToDay
        displayWeatherToDay(weatherToDay)
    }

    private fun displayWeatherToDay(weatherToDay: WeatherToDay) {
        weatherToDay.apply {
            binding.weatherToDayCityName.text = city?.city
            binding.weatherToDayDate.text = date
            binding.weatherToDayIcon.loadUrl("https://yastatic.net/weather/i/icons/funky/dark/${icon}.svg")
            binding.weatherToDayTemperature.text = temperature.toString() + "°"
            binding.weatherToDayCondition.text = condition
            binding.weatherToDayTempMinMax.text = temperature_min_max
            binding.weatherToDayFeelsLike.text = feels_like.toString() + "°"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null!!
    }
    companion object {
        fun newInstance(weatherToDay: WeatherToDay): WeatherToDayFragment {
            val fragment = WeatherToDayFragment()
            val bundle = Bundle()
            bundle.putParcelable(WEATHER_TO_DAY_KEY,weatherToDay)
            fragment.arguments = bundle
            return fragment
        }
    }

}
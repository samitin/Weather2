package ru.samitin.weather.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.samitin.weather.databinding.FragmentWeatherPartBinding
import ru.samitin.weather.model.data.WeatherPart


class WeatherPartFragment : Fragment() {
    private lateinit var weatherPart: WeatherPart
    private lateinit var _binding: FragmentWeatherPartBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherPartBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherPart = arguments?.getParcelable<WeatherPart>(WEATHER_PART_KEY) as WeatherPart
        renderData(weatherPart)
    }

    private fun renderData(weatherPart: WeatherPart) {
        weatherPart.apply {
            binding.partName.text = part_name
            binding.tempMin.text = temp_min.toString() + "°C"
            binding.tempMax.text = temp_max.toString() +"°C"
            binding.tempAvg.text = temp_avg.toString() + "°C"
            binding.feelsLike.text = feels_like.toString() + "°C"
            binding.condition.text = condition
            binding.windSpeed.text = wind_speed.toString() + "м/с"
            binding.windGust.text = wind_gust.toString() + "м/с"
            binding.windDir.text = wind_dir
            binding.pressureMm.text = pressure_mm.toString()+"мм"
            binding.humidity.text = humidity.toString() + "%"
            binding.precMm.text = prec_mm.toString() + "мм"
            binding.precPeriod.text = prec_period.toString() + "мин."
            binding.precProb.text = prec_prob.toString()
            
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null!!
    }
    companion object {
        private const val WEATHER_PART_KEY = "WEATHER_FORECAST_KEY"
        fun newInstance(part: WeatherPart) =
            WeatherPartFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(WEATHER_PART_KEY, part)
                }
            }
    }
}
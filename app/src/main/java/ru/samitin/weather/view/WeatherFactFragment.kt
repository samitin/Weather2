package ru.samitin.weather.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.samitin.weather.databinding.FragmentWeatherFactBinding

import ru.samitin.weather.model.data.WeatherFact

class WeatherFactFragment : Fragment() {
    private lateinit var weatherFact: WeatherFact
    private lateinit var _binding: FragmentWeatherFactBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherFactBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherFact = arguments?.getParcelable<WeatherFact>(WEATHER_FACT_KEY) as WeatherFact
        renderData(weatherFact)
    }

    @SuppressLint("SetTextI18n")
    private fun renderData(weatherFact: WeatherFact) {
        weatherFact.apply {
            binding.windSpeed.text = windSpeed.toString() + "m/s"
            binding.windGast.text = windGust.toString() + "m/s"
            binding.windDir.text = windDir
            binding.daytime.text = daytime
            binding.humidity.text = humidity.toString() + "%"
            binding.pressureMm.text = pressureMM.toString() + "mm"
            binding.pressurePa.text = pressurePA.toString()
            binding.season.text = season
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null!!
    }
    companion object {
        private const val WEATHER_FACT_KEY = "WEATHER_FACT_KEY"
        fun newInstance(fact: WeatherFact) =
            WeatherFactFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(WEATHER_FACT_KEY, fact)
                }
            }
    }
}
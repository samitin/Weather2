package ru.samitin.weather.view.searchCity

import android.content.Context
import android.location.Address
import android.location.Geocoder

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import kotlinx.coroutines.*
import ru.samitin.weather.R

import ru.samitin.weather.databinding.FragmentSearchBinding
import ru.samitin.weather.model.data.City
import ru.samitin.weather.view.main.MainActivity
import java.lang.ClassCastException

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }
    private lateinit var _binding :FragmentSearchBinding
    private val binding get() = _binding


    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val geocoder=Geocoder(context)
        initEditText(geocoder)
        val mainactivity =activity as MainActivity

    }

    private fun initEditText(geocoder: Geocoder) {
        binding.textInputEditText.addTextChangedListener(
            object : TextWatcher {
                //до изменения текста
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int, ) {

                }
                //при изменении текста
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }
                //после изменения текста
                override fun afterTextChanged(s: Editable?) {
                    if (!s.isNullOrEmpty()) {
                        try {
                            runBlocking{
                                launch {
                                    val listAddress = getListAddress(geocoder,s.toString())
                                    if (!listAddress.isNullOrEmpty())
                                        showSearchAddress(listAddress)
                                }
                            }
                        }catch (e:Exception){

                        }
                    }
                }
            }
        )
    }
    suspend fun getListAddress(geocoder: Geocoder,s : String) = geocoder.getFromLocationName(s.toString(), 6)

    fun showSearchAddress(adreses :List<Address> ){

        binding.searchConteiner.removeAllViews()
        for (adres in adreses){
            var text :AppCompatTextView = LayoutInflater.from(context).inflate(R.layout.item_search,null) as AppCompatTextView
            text.text = adres.featureName
            binding.searchConteiner.addView(text)
            text.setOnClickListener {
                Toast.makeText(context,"${adres.featureName} lat=${adres.latitude} lon=${adres.longitude}",Toast.LENGTH_SHORT).show()
                try {
                    val postman = activity as Postman
                    postman.fragmentMail(City(adres.featureName,adres.latitude,adres.longitude))
                }catch (e:ClassCastException){

                }
            }
        }

    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null!!
    }

}
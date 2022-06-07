package ru.samitin.weather.view.main

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.navigation.NavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.samitin.core.base.BaseActivity
import ru.samitin.weather.utils.network.isOnline
import ru.samitin.weather.R
import ru.samitin.weather.databinding.MainActivityBinding
import ru.samitin.weather.model.AppState
import ru.samitin.weather.model.data.City
import ru.samitin.weather.model.data.Weather
import ru.samitin.weather.model.data.getDefaultCity
import ru.samitin.weather.view.*
import ru.samitin.weather.view.searchCity.Postman
import ru.samitin.weather.view.searchCity.SearchFragment

import ru.samitin.weather.viewmodel.MainViewModel

private const val FAVORITE_CITY_NAME_KEY = "FAVORITE_CITY_NAME_KEY"
private const val FAVORITE_CITY_LAT_KEY = "FAVORITE_CITY_LAT_KEY"
private const val FAVORITE_CITY_LON_KEY = "FAVORITE_CITY_LON_KEY"
class MainActivity : BaseActivity<AppState, MainInteractor>(),Postman
 {
    private lateinit var binding : MainActivityBinding
    private lateinit var city:City
    private lateinit var sharedPref :SharedPreferences
    override val model: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = getSharedPreferences("CITY_NAME", MODE_PRIVATE)
        city = getValueCity()
        iniViewModel()
        initToolbarAndDrawer()
        model.getData(city, isOnline(this))

    }
     fun saveFavoriteCity(city: City){

         sharedPref.edit().apply {
             putString(FAVORITE_CITY_NAME_KEY,city.city)
             putFloat(FAVORITE_CITY_LAT_KEY,city.lat.toFloat())
             putFloat(FAVORITE_CITY_LON_KEY,city.lon.toFloat())
             commit()
         }
     }
     fun getValueCity() : City{
         return City(
             sharedPref.getString(FAVORITE_CITY_NAME_KEY,"")!!,
             sharedPref.getFloat(FAVORITE_CITY_LAT_KEY,0.0F).toDouble(),
             sharedPref.getFloat(FAVORITE_CITY_LON_KEY,0.0f).toDouble()
         )
     }

     private fun iniViewModel() {
         model.subscribe().observe(this@MainActivity, Observer<AppState> { renderData(it) })
     }
     private fun initToolbarAndDrawer() {
         val toolbar : Toolbar = findViewById(R.id.toolbar)
         setSupportActionBar(toolbar)
         initDrawer(toolbar)

     }

     private fun initDrawer(toolbar: Toolbar) {
         val drawer : DrawerLayout = findViewById(R.id.drawer_layout)
         val toggle  = ActionBarDrawerToggle(
             this,
             drawer,
             toolbar,
             R.string.navigation_drawer_open,
             R.string.navigation_drawer_close
         )
         toggle.drawerArrowDrawable.color = getColor(R.color.black);
         drawer.addDrawerListener(toggle)
         toggle.syncState()

         // Обработка навигационного меню
         val navigationView : NavigationView = findViewById(R.id.navigation_view)
         navigationView.setNavigationItemSelectedListener {
             when(it.itemId){
                 R.id.action_drawer_about ->{

                     drawer.closeDrawer(GravityCompat.START)
                     return@setNavigationItemSelectedListener true
                 }
                 R.id.action_drawer_exit ->{

                     drawer.closeDrawer(GravityCompat.START)
                     return@setNavigationItemSelectedListener true
                 }else -> return@setNavigationItemSelectedListener false
             }
         }
         val headerLayout : View =navigationView.getHeaderView(0)
         initHeaderLayout(headerLayout)
     }

     private fun initHeaderLayout(headerLayout: View) {
         val cityName:AppCompatTextView = headerLayout.findViewById(R.id.city_name)
         cityName.text = city.city
         val searchCityTextView : AppCompatTextView = headerLayout.findViewById(R.id.add_city)
         searchCityTextView.setOnClickListener {
             Toast.makeText(this,"Add City",Toast.LENGTH_SHORT).show()
         }
     }

     override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.menu_actionbar,menu)
         return true
     }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         if (item.itemId == R.id.item_add){
             addFragment(SearchFragment.newInstance())
             return true
         }
         return false
     }
     fun addFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .addToBackStack("")
            .replace(binding.fragmentContainer.id,fragment)
            .commit()
    }



     override fun setData(data: Weather) {
         addFragment(WeatherFragment.newInstance(data))
     }

     override fun fragmentMail(city: City) {
         saveFavoriteCity(city)
         this.city=city
         model.getData(city, isOnline(this))
         initToolbarAndDrawer()
     }


 }
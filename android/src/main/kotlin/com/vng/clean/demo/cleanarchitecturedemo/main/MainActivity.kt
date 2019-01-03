package com.vng.clean.demo.cleanarchitecturedemo.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.vng.clean.demo.cleanarchitecturedemo.Injector
import com.vng.clean.demo.cleanarchitecturedemo.R
import com.vng.clean.demo.cleanarchitecturedemo.Resource
import com.vng.clean.demo.cleanarchitecturedemo.model.WeatherInfoModel
import com.vng.clean.demo.cleanarchitecturedemo.ui.WeatherAdapter
import kotlinx.android.synthetic.main.activity_test.*

class MainActivity: AppCompatActivity() {

    private val weatherAdapter = WeatherAdapter()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        setupRecyclerView()
        setupViewModel()
        mainViewModel.fetchWeatherList(10.8018864,106.6358784)
    }

    private fun setupViewModel() {
        val factory = Injector.getMainViewModelFactory()
        mainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        mainViewModel.weatherListLiveData().observe(this, Observer<Resource<List<WeatherInfoModel>>> {
            t -> processWeatherListResource(t)
        })
    }

    private fun processWeatherListResource(resource: Resource<List<WeatherInfoModel>>?) {
        resource?.let {
            when(it.status) {
                Resource.Status.LOADING -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_LONG).show()
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                Resource.Status.SUCCESS -> {
                    weatherAdapter.submitList(it.data)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        mainList.layoutManager = layoutManager
        mainList.adapter = weatherAdapter
    }
}
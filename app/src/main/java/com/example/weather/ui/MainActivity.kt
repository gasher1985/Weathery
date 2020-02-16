package com.example.weather.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.weather.R
import com.example.weather.internal.gps.LocationLiveData
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

private const val MY_PERMISSION_ACCESS_COARSE_LOCATION = 1
class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        if (!hasLocationPermission())
            navController.navigate(R.id.splashScreen)

    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }


}

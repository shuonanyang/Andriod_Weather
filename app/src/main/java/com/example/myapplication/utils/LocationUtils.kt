package com.example.myapplication.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
//获取最后已知位置的LocationUtils类，您需要确保已经处理了所有必要的权限请求。
// LocationUtils类实现，它包括权限检查和获取最后已知位置的功能
class LocationUtils(private val context: Context) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    fun getLastKnownLocation(): Task<Location>? {
        return if (checkLocationPermission()) {
            fusedLocationClient.lastLocation
        } else {
            null // No location is returned if permission isn't granted
        }
    }

    private fun checkLocationPermission(): Boolean {
        // Checking if the location permission has been granted
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}

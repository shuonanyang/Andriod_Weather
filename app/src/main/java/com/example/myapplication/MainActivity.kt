package com.example.myapplication

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.viewmodels.LocationViewModel
import com.example.myapplication.utils.LocationUtils


/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}
*/

class MainActivity : ComponentActivity() {
    // 使用 viewModels() 委托获取 ViewModel 的实例
    private val LocationViewModel: LocationViewModel by viewModels()

    companion object {
        const val MY_PERMISSIONS_REQUEST_LOCATION = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 在这里检查位置权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            // 如果权限尚未被授予，则请求权限
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_PERMISSIONS_REQUEST_LOCATION
            )
        }

        setContent {
            MyApp(locationViewModel = LocationViewModel)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // 权限被授予，可以执行获取位置的操作
                    // 由于已经检查了权限，可以调用获取位置的方法
                    getLocationAndUpdateUI()
                } else {
                    // 权限被拒绝，向用户解释无法执行相关功能
                    // 在这里处理权限被拒绝的情况
                }
            }
        }
    }

    private fun getLocationAndUpdateUI() {
        // 使用LocationUtils来获取位置，并更新界面
        // 确保已经检查了权限
        val locationTask = LocationUtils(this).getLastKnownLocation()
        locationTask?.addOnSuccessListener { location ->
            // 使用location更新UI
            // 这里你可以更新你的ViewModel或者直接更新UI组件
        }
    }
}

/*
@Composable
fun MyApp(navController: NavController) {
    NavHost(navController = navController as NavHostController, startDestination = "cityWeather") {
        composable("cityWeather") { CityWeatherScreen(navController) }
        // Add more routes here
    }
}*/
@Composable
fun LocationScreen(locationViewModel: LocationViewModel) {
    val location by locationViewModel.location.observeAsState()

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        location?.let {
            Text(text = "Current location: (${it.latitude}, ${it.longitude})")
        } ?: run {
            Text(text = "Waiting for location...")
        }
    }
}

@Composable
fun MyApp(locationViewModel: LocationViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "locationDisplay") {
        composable("locationDisplay") { LocationScreen(locationViewModel) }
        // Add more routes here
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}



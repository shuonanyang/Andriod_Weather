package com.example.myapplication.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.myapplication.ui.viewmodels.SettingsViewModel


@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val temperatureUnit by viewModel.temperatureUnit.observeAsState(initial = true)
    // 使用 temperatureUnit 来更新UI，例如显示一个开关来让用户选择温度单位
}

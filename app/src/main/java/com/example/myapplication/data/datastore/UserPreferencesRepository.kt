package com.example.myapplication.data.datastore
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
//创建一个简单的 DataStore 管理类来封装对用户首选项的读写操作。以下是一个如何使用 Preferences DataStore 存储和读取用户首选项（例如，用户是否喜欢使用摄氏度
// 创建一个顶级属性，以避免每次需要访问DataStore时都创建新的实例
val Context.dataStore by preferencesDataStore(name = "settings")

class UserPreferencesRepository(context: Context) {
    private val appContext = context.applicationContext

    // 定义一个键，用于访问存储的值
    companion object {
        val TEMPERATURE_UNIT_KEY = booleanPreferencesKey("temperature_unit_celsius")
    }

    // 读取用户首选项
    val temperatureUnit: Flow<Boolean> = appContext.dataStore.data
        .map { preferences ->
            // 如果未设置，默认为 true（摄氏度）
            preferences[TEMPERATURE_UNIT_KEY] ?: true
        }

    // 更新用户首选项
    suspend fun updateTemperatureUnit(useCelsius: Boolean) {
        appContext.dataStore.edit { preferences ->
            preferences[TEMPERATURE_UNIT_KEY] = useCelsius
        }
    }
}

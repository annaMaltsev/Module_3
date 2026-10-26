package com.example.module_3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import com.example.module_3.Module_3_4.CounterScreen
import com.example.module_3.Module_3_5.AppNavigation
import com.example.module_3.Module_3_5.FirstScreen
import com.example.module_3.Module_3_6.AdaptiveLayout
import com.example.module_3.Module_3_7.presentation.AppNavigation as TodoAppNavigation
import com.example.module_3.ui.theme.Module_3Theme

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivityLifecycle"
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val windowSize = calculateWindowSizeClass(this)
            Module_3Theme {
                TodoAppNavigation()
                Log.d(TAG, "MainActivity создана")
            }
        }
    }
//    override fun onStart() {
//        super.onStart()
//        Log.d(TAG, "MainActivity запущена")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d(TAG, "MainActivity работает")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d(TAG, "MainActivity поставлена на паузу")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d(TAG, "MainActivity остановлена")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d(TAG, "MainActivity уничтожена")
//    }
}

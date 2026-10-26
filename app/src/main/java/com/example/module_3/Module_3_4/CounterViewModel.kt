package com.example.module_3.Module_3_4

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    var count by mutableIntStateOf(0)
        private set
    fun inc() = count++
    fun dec() = count--
}

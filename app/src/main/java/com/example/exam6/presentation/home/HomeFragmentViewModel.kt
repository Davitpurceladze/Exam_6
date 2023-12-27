package com.example.exam6.presentation.home

import android.app.usage.UsageEvents.Event
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.exam6.R
import kotlinx.coroutines.flow.MutableStateFlow

class HomeFragmentViewModel : ViewModel() {
    private val buttonsData: List<ButtonType> =
        listOf(
            ButtonType(1, 1),
            ButtonType(2, 2),
            ButtonType(3, 3),
            ButtonType(4, 4),
            ButtonType(5, 5),
            ButtonType(6, 6),
            ButtonType(7, 7),
            ButtonType(8, 8),
            ButtonType(9, 9),
            ButtonType(10, R.drawable.ic_touch),
            ButtonType(11, 0),
            ButtonType(12, R.drawable.vector)
        )

    private val _passwordFlow = MutableStateFlow<String?>("")
    val passwordFlow: MutableStateFlow<String?> get() = _passwordFlow

    fun getButtonsData(): List<ButtonType> {
        return buttonsData
    }

    fun password(int: Int) {
        _passwordFlow.value = passwordFlow.value.plus(int)
        println(passwordFlow.value)

        if(passwordFlow.value?.length!! >= 4) {
            _passwordFlow.value = ""
        }
    }

}
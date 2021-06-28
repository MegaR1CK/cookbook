package com.hfad.cookbook.ui.splash_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val _navigateToHomeEvent = MutableLiveData<Boolean>()
    val navigateToHomeEvent: LiveData<Boolean>
        get() = _navigateToHomeEvent

    init {
        viewModelScope.launch {
            delay(1500)
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        _navigateToHomeEvent.value = true
    }

    fun navigatedToHome() {
        _navigateToHomeEvent.value = false
    }
}
package com.hfad.cookbook.splash_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hfad.cookbook.base.CoroutinesViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : CoroutinesViewModel() {
    
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
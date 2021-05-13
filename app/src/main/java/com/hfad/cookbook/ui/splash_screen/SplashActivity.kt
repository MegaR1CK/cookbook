package com.hfad.cookbook.ui.splash_screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hfad.cookbook.R
import com.hfad.cookbook.ui.main_screen.HomeActivity
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.navigateToHomeEvent.observe(this) {
            if (it) {
                startActivity(Intent(this, HomeActivity::class.java))
                viewModel.navigatedToHome()
                finish()
            }
        }
    }
}
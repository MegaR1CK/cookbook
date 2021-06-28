package com.hfad.cookbook.ui.splash_screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hfad.cookbook.R
import com.hfad.cookbook.ui.HomeActivity
import com.hfad.cookbook.ui.theme.CookbookTheme
import com.hfad.cookbook.ui.theme.Courgette
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private val splashViewModel by inject<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen()
        }
        splashViewModel.navigateToHomeEvent.observe(this) {
            if (it) {
                startActivity(Intent(this, HomeActivity::class.java))
                splashViewModel.navigatedToHome()
                finish()
            }
        }
    }
}

@Composable
fun SplashScreen() {
    CookbookTheme {
        Surface(color = MaterialTheme.colors.primary, modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.cookbook_logo),
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.h3,
                    fontFamily = Courgette
                )
            }
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}
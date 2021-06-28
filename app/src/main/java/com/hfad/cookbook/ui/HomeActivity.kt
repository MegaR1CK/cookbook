package com.hfad.cookbook.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.hfad.cookbook.R
import com.hfad.cookbook.ui.main_screen.RecipesScreenEntry
import com.hfad.cookbook.ui.main_screen.RecipesViewModel
import com.hfad.cookbook.ui.theme.CookbookTheme
import com.hfad.cookbook.ui.theme.Courgette
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private val recipesViewModel by inject<RecipesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookbookApp(recipesViewModel)
        }
    }
}

@Composable
fun CookbookApp(recipesViewModel: RecipesViewModel) {
    CookbookTheme {
        Scaffold(
            topBar = {
                TopAppBar {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center,
                        fontFamily = Courgette,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        ) {
            RecipesScreenEntry(recipesViewModel = recipesViewModel)
        }
    }
}



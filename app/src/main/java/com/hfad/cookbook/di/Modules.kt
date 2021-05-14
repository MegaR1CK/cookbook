package com.hfad.cookbook.di

import com.hfad.cookbook.data.database.RecipesDatabase
import com.hfad.cookbook.repository.RecipeCardsRepository
import com.hfad.cookbook.ui.main_screen.RecipeCardsAdapter
import com.hfad.cookbook.ui.main_screen.popular.PopularViewModel
import com.hfad.cookbook.ui.splash_screen.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { PopularViewModel() }
}
val dataModule = module {
    single { RecipesDatabase.getDatabase(androidContext()) }
    single { RecipeCardsRepository(get()) }
}
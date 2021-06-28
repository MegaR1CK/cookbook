package com.hfad.cookbook.di

import com.hfad.cookbook.data.network.RecipesPagingSource
import com.hfad.cookbook.repository.RecipeCardsRepository
import com.hfad.cookbook.ui.main_screen.RecipesViewModel
import com.hfad.cookbook.ui.splash_screen.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RecipesViewModel() }
    viewModel { SplashViewModel() }
}
val dataModule = module {
    single { RecipesPagingSource() }
    single { RecipeCardsRepository(get()) }
}
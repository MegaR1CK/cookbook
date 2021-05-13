package com.hfad.cookbook.ui.main_screen.popular

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.cookbook.repository.RecipeCardsRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PopularViewModel : ViewModel(), KoinComponent {

    private val repository: RecipeCardsRepository by inject()

    val recipeCards = repository.recipeCards

    init {
        viewModelScope.launch {
            repository.refreshRecipeCards()
        }
    }
}
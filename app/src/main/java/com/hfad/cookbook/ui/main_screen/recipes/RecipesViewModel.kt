package com.hfad.cookbook.ui.main_screen.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.cookbook.repository.RecipeCardsRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RecipesViewModel : ViewModel(), KoinComponent {

    private val repository: RecipeCardsRepository by inject()

    val recipeCards = repository.recipeCards
    val status = repository.status

    init {
        loadRecipeCards()
    }

    fun loadMoreRecipeCards() = viewModelScope.launch {
       repository.loadMoreRecipeCards()
    }

    fun loadRecipeCards() = viewModelScope.launch {
        repository.loadRecipeCards()
    }
}
package com.hfad.cookbook.ui.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hfad.cookbook.repository.RecipeCardsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RecipesViewModel : ViewModel(), KoinComponent {

    private val repository: RecipeCardsRepository by inject()

    val recipeCards = repository.recipeCards.cachedIn(viewModelScope)
}
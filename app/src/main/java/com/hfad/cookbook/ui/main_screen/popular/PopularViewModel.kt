package com.hfad.cookbook.ui.main_screen.popular

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
        if (recipeCards.value == null) {
            loadNewRecipeCards(50)
        }
    }

    fun loadNewRecipeCards(number: Int) = viewModelScope.launch {
        repository.loadNewRecipeCards(number)
    }
}
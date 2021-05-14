package com.hfad.cookbook.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.hfad.cookbook.data.asDatabaseModel
import com.hfad.cookbook.data.asDomainModel
import com.hfad.cookbook.data.database.RecipesDatabase
import com.hfad.cookbook.data.domain.RecipeCard
import com.hfad.cookbook.data.network.RecipesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeCardsRepository(private val database: RecipesDatabase) {
    val recipeCards: LiveData<List<RecipeCard>> =
        Transformations.map(database.cachedRecipeCardsDao.getRecipeCards()) {
            it.asDomainModel()
        }

    suspend fun refreshRecipeCards() = withContext(Dispatchers.IO) {
        val cards = RecipesApi.recipeService.getPopularRecipes("20", null)
        database.cachedRecipeCardsDao.insertRecipeCards(*cards.asDatabaseModel())
    }

}
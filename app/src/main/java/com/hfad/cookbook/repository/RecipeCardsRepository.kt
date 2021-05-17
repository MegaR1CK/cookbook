package com.hfad.cookbook.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.hfad.cookbook.data.asDatabaseModel
import com.hfad.cookbook.data.asDomainModel
import com.hfad.cookbook.data.database.RecipesDatabase
import com.hfad.cookbook.data.domain.RecipeCard
import com.hfad.cookbook.data.network.RecipesApi

class RecipeCardsRepository(private val database: RecipesDatabase) {

    private val _recipeCards: MutableLiveData<List<RecipeCard>> =
        Transformations.map(database.cachedRecipeCardsDao.getRecipeCards()) {
        it.asDomainModel()
        } as MutableLiveData
    val recipeCards: LiveData<List<RecipeCard>>
        get() = _recipeCards

    private val _status = MutableLiveData<RecipeApiStatus>()
    val status: LiveData<RecipeApiStatus>
        get() = _status

    suspend fun loadRecipeCards() {
        try {
            _status.value = RecipeApiStatus.LOADING_FIRST
            val isDatabaseEmpty = database.cachedRecipeCardsDao.getRecipeCardsCount() == 0
            if (isDatabaseEmpty) {
                requestRecipeCards()
            }
            _status.value = RecipeApiStatus.DONE
        }
        catch (e: Exception) {
            _status.value = RecipeApiStatus.ERROR
            _recipeCards.value = listOf()
        }
    }

    suspend fun loadMoreRecipeCards() {
        try {
            _status.value = RecipeApiStatus.LOADING_MORE
            requestRecipeCards()
            _status.value = RecipeApiStatus.DONE
        }
        catch (e: Exception) {
            _status.value = RecipeApiStatus.ERROR
        }
    }

    private suspend fun requestRecipeCards() {
        val result = RecipesApi.recipeService.getPopularRecipes("50", null).asDatabaseModel()
        database.cachedRecipeCardsDao.insertRecipeCards(*result)
    }

    enum class RecipeApiStatus { LOADING_FIRST, LOADING_MORE, DONE, ERROR}
}
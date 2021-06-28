package com.hfad.cookbook.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState

class RecipesPagingSource : PagingSource<Int, NetworkRecipe>() {

    companion object {
        private const val RECIPES_STARTING_PAGE_INDEX = 1
    }


    override fun getRefreshKey(state: PagingState<Int, NetworkRecipe>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkRecipe> {
        val position = params.key ?: RECIPES_STARTING_PAGE_INDEX

        return try {
            val recipes = RecipesApi.recipeService.getPopularRecipes("20", null).recipes
            LoadResult.Page(
                data = recipes,
                prevKey = if (position == RECIPES_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (recipes.isEmpty()) null else position + 1
            )
        }
        catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}
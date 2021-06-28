package com.hfad.cookbook.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.hfad.cookbook.data.asDomainModel
import com.hfad.cookbook.data.domain.RecipeCard
import com.hfad.cookbook.data.network.RecipesPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent

class RecipeCardsRepository(private val pagingSource: RecipesPagingSource) : KoinComponent {

    val recipeCards = getRecipes()

    private fun getRecipes(): Flow<PagingData<RecipeCard>> {
        val networkPagingData = Pager(PagingConfig(20, enablePlaceholders = false)) { pagingSource }.flow
        return networkPagingData.map {
            it.map {
                it.asDomainModel()
            }
        }
    }
}
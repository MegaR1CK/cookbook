package com.hfad.cookbook.data

import com.hfad.cookbook.data.domain.RecipeCard
import com.hfad.cookbook.data.network.NetworkRecipe

fun NetworkRecipe.asDomainModel(): RecipeCard {
    return RecipeCard(
        id ?: 0,
        title ?: "",
        image ?: "",
        readyInMinutes ?: 0,
        spoonacularScore ?: 0.0
    )
}
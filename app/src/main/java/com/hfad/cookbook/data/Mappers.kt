package com.hfad.cookbook.data

import com.hfad.cookbook.data.database.DatabaseRecipeCard
import com.hfad.cookbook.data.domain.RecipeCard
import com.hfad.cookbook.data.network.NetworkRecipeContainer

fun NetworkRecipeContainer.asDatabaseModel(): Array<DatabaseRecipeCard> {
    return recipes.map {
        DatabaseRecipeCard(
            id = 0,
            title = it.title ?: "",
            imageUrl = it.image ?: ""
        )
    }.toTypedArray()
}

fun List<DatabaseRecipeCard>.asDomainModel(): List<RecipeCard> {
    return map { RecipeCard(it.id, it.title, it.imageUrl) }
}
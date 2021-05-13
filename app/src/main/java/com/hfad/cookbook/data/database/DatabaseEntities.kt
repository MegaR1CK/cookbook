package com.hfad.cookbook.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "cached_recipe_cards")
data class DatabaseRecipeCard(
    @PrimaryKey
    val id: Int,
    val title: String,
    val imageUrl: String,
)
package com.hfad.cookbook.data.domain

data class RecipeCard(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val readyInMinutes: Int,
    val rating: Double
)
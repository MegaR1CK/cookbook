package com.hfad.cookbook.ui.main_screen

import com.hfad.cookbook.data.domain.RecipeCard

sealed class DataItem {
    abstract val id: Long
    data class RecipeCardItem(val recipeCard: RecipeCard) : DataItem() {
        override val id = recipeCard.id.toLong()
    }

    object Footer : DataItem() {
        override val id = Long.MIN_VALUE
    }
}
package com.hfad.cookbook.ui.main_screen.recipe_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.cookbook.data.domain.RecipeCard
import com.hfad.cookbook.databinding.RecipeCardBinding

class RecipeCardViewHolder private constructor(private val binding: RecipeCardBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): RecipeCardViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = RecipeCardBinding.inflate(inflater, parent, false)
            return RecipeCardViewHolder(binding)
        }
    }

    fun bind(card: RecipeCard) {
        binding.recipeCard = card
        binding.executePendingBindings()
    }
}
package com.hfad.cookbook.ui.main_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.cookbook.data.domain.RecipeCard
import com.hfad.cookbook.databinding.RecipeCardBinding

class RecipeCardsAdapter :
    ListAdapter<RecipeCard, RecipeCardsAdapter.RecipeCardViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<RecipeCard>() {
        override fun areItemsTheSame(oldItem: RecipeCard, newItem: RecipeCard): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RecipeCard, newItem: RecipeCard): Boolean {
            return oldItem == newItem
        }
    }

    class RecipeCardViewHolder(private val binding: RecipeCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: RecipeCard) {
            binding.recipeCard = card
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeCardViewHolder {
        return RecipeCardViewHolder(RecipeCardBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecipeCardViewHolder, position: Int) {
        val card = getItem(position)
        holder.bind(card)
    }
}
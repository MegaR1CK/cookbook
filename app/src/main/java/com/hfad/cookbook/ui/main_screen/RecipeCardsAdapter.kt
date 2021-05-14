package com.hfad.cookbook.ui.main_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.cookbook.R
import com.hfad.cookbook.data.domain.RecipeCard
import com.hfad.cookbook.databinding.RecipeCardBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeCardsAdapter :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(DiffCallback) {

    private val ITEM_VIEW_TYPE_FOOTER = 0
    private val ITEM_VIEW_TYPE_ITEM = 1
    private val adapterScope = CoroutineScope(Dispatchers.Default)

    companion object DiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }

    class RecipeCardViewHolder private constructor(private val binding: RecipeCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: RecipeCard) {
            binding.recipeCard = card
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RecipeCardViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = RecipeCardBinding.inflate(inflater, parent, false)
                return RecipeCardViewHolder(binding)
            }
        }
    }

    class FooterViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): FooterViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.recipe_list_footer, parent, false)
                return FooterViewHolder(view)
            }
        }
    }

    fun addFooterAndSubmitList(list: List<RecipeCard>?) {
        adapterScope.launch {
            val items = list?.map { DataItem.RecipeCardItem(it) }?.plus(listOf(DataItem.Footer))
            withContext(Dispatchers.Main) { submitList(items) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is DataItem.RecipeCardItem -> ITEM_VIEW_TYPE_ITEM
            is DataItem.Footer -> ITEM_VIEW_TYPE_FOOTER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_ITEM -> RecipeCardViewHolder.from(parent)
            ITEM_VIEW_TYPE_FOOTER -> FooterViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RecipeCardViewHolder -> {
                val item = getItem(position) as DataItem.RecipeCardItem
                holder.bind(item.recipeCard)
            }
        }
    }
}
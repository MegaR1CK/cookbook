package com.hfad.cookbook.ui.main_screen.recipe_list

import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.cookbook.data.domain.RecipeCard
import com.hfad.cookbook.repository.RecipeCardsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeCardsAdapter(private val footerClickListener: FooterButtonClickListener,
                         private val status: LiveData<RecipeCardsRepository.RecipeApiStatus>) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }

    private val ITEM_VIEW_TYPE_FOOTER = 0
    private val ITEM_VIEW_TYPE_ITEM = 1
    private val adapterScope = CoroutineScope(Dispatchers.Default)
    private lateinit var footerHolder: FooterViewHolder

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is DataItem.RecipeCardItem -> ITEM_VIEW_TYPE_ITEM
            is DataItem.Footer -> ITEM_VIEW_TYPE_FOOTER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_ITEM -> RecipeCardViewHolder.from(parent)
            ITEM_VIEW_TYPE_FOOTER -> {
                footerHolder = FooterViewHolder.from(parent)
                footerHolder.markCreated()
                footerHolder
            }
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RecipeCardViewHolder -> {
                val item = getItem(position) as DataItem.RecipeCardItem
                holder.bind(item.recipeCard)
            }
            is FooterViewHolder -> holder.bind(footerClickListener, status)
        }
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        if (holder is FooterViewHolder) { holder.markAttach() }
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        if (holder is FooterViewHolder) { holder.markDetach() }
    }

    fun setLifecycleDestroyed() = footerHolder.markDestroyed()

    fun addFooterAndSubmitList(list: List<RecipeCard>?) {
        adapterScope.launch {
            val items = list?.map { DataItem.RecipeCardItem(it) }?.plus(listOf(DataItem.Footer))
            withContext(Dispatchers.Main) { submitList(items) }
        }
    }

    class FooterButtonClickListener(private val clickListener: () -> Unit) {
        fun onClick() = clickListener.invoke()
    }
}
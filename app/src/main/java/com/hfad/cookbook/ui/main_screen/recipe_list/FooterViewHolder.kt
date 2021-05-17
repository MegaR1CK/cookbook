package com.hfad.cookbook.ui.main_screen.recipe_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.hfad.cookbook.databinding.RecipeListFooterBinding
import com.hfad.cookbook.repository.RecipeCardsRepository

class FooterViewHolder private constructor(private val binding: RecipeListFooterBinding) :
    RecyclerView.ViewHolder(binding.root), LifecycleOwner {

    companion object {
        fun from(parent: ViewGroup): FooterViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = RecipeListFooterBinding.inflate(inflater, parent, false)
            val holder = FooterViewHolder(binding)
            binding.lifecycleOwner = holder
            return holder
        }
    }

    private val lifecycleRegistry = LifecycleRegistry(this)
    private var wasPaused = false

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }

    override fun getLifecycle() = lifecycleRegistry

    fun markCreated() {
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    fun markAttach() {
        if (wasPaused) {
            lifecycleRegistry.currentState = Lifecycle.State.RESUMED
            wasPaused = false
        }
        else {
            lifecycleRegistry.currentState = Lifecycle.State.STARTED
        }
    }

    fun markDetach() {
        wasPaused = true
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    fun markDestroyed() {
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

    fun bind(clickListener: RecipeCardsAdapter.FooterButtonClickListener, status: LiveData<RecipeCardsRepository.RecipeApiStatus>) {
        binding.clickListener = clickListener
        binding.status = status
    }
}
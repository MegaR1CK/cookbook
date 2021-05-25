package com.hfad.cookbook.ui.main_screen.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.hfad.cookbook.R
import com.hfad.cookbook.databinding.FragmentRecipesBinding
import com.hfad.cookbook.repository.RecipeCardsRepository
import com.hfad.cookbook.ui.main_screen.recipe_list.RecipeCardsAdapter
import org.koin.android.ext.android.inject

class RecipesFragment : Fragment() {

    companion object {
        fun newInstance() = RecipesFragment()
    }

    private val viewModel: RecipesViewModel by inject()
    private lateinit var adapter: RecipeCardsAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding = FragmentRecipesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setupList(binding)

        viewModel.status.observe(viewLifecycleOwner) {
            if (it == RecipeCardsRepository.RecipeApiStatus.ERROR) {
                Snackbar.make(
                    activity?.findViewById(android.R.id.content) as View,
                    it.message ?: getString(R.string.unknown_error),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter.setLifecycleDestroyed()
    }

    private fun setupList(binding: FragmentRecipesBinding) {
        adapter = RecipeCardsAdapter(RecipeCardsAdapter.FooterButtonClickListener {
            viewModel.loadMoreRecipeCards()
        }, viewModel.status)
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                if (adapter.itemCount == 51) {
                    binding.recipeCardsList.scrollToPosition(0)
                }
            }
        })
        binding.recipeCardsList.adapter = adapter

        val manager = binding.recipeCardsList.layoutManager as GridLayoutManager
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) = when (position) {
                adapter.itemCount - 1 -> 2
                else -> 1
            }
        }
    }
}
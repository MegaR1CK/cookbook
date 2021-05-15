package com.hfad.cookbook.ui.main_screen.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.cookbook.databinding.FragmentPopularBinding
import com.hfad.cookbook.ui.main_screen.RecipeCardsAdapter
import org.koin.android.ext.android.inject

class PopularFragment : Fragment() {

    companion object {
        fun newInstance() = PopularFragment()
    }

    private val viewModel: PopularViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding = FragmentPopularBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = RecipeCardsAdapter(RecipeCardsAdapter.FooterButtonClickListener {
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
        return binding.root
    }
}
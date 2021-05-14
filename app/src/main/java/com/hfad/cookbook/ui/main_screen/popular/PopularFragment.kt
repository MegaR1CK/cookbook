package com.hfad.cookbook.ui.main_screen.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hfad.cookbook.databinding.FragmentPopularBinding
import com.hfad.cookbook.ui.main_screen.RecipeCardsAdapter
import org.koin.android.ext.android.inject

class PopularFragment : Fragment() {

    companion object {
        fun newInstance() = PopularFragment()
    }

    private val viewModel: PopularViewModel by inject()
    private val adapter: RecipeCardsAdapter by inject()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentPopularBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recipeCardsList.adapter = adapter

        return binding.root
    }

}
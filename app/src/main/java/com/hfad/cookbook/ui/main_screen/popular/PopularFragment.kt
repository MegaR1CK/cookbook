package com.hfad.cookbook.ui.main_screen.popular

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hfad.cookbook.R
import org.koin.android.ext.android.inject

class PopularFragment : Fragment() {

    companion object {
        fun newInstance() = PopularFragment()
    }

    private val viewModel: PopularViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel.recipeCards.observe(viewLifecycleOwner) {
            Log.d("APP", it.first().title)
            Log.d("APP", it.first().imageUrl)
        }

        return inflater.inflate(R.layout.popular_fragment, container, false)
    }

}
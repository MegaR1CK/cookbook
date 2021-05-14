package com.hfad.cookbook.ui.main_screen.featured

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hfad.cookbook.R

class FeaturedFragment : Fragment() {

    companion object {
        fun newInstance() = FeaturedFragment()
    }

    private lateinit var viewModel: FeaturedViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_featured, container, false)
    }
}
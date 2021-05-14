package com.hfad.cookbook.ui

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hfad.cookbook.R
import com.hfad.cookbook.data.domain.RecipeCard
import com.hfad.cookbook.ui.main_screen.RecipeCardsAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val context = imgView.context
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(context)
            .load(imgUri)
            .placeholder(R.drawable.recipe_card_placeholder)
            .into(imgView)
    }
}

@BindingAdapter("dataList")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<RecipeCard>?) {
    val adapter = recyclerView.adapter as RecipeCardsAdapter
    adapter.submitList(data)
}
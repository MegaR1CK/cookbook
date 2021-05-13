package com.hfad.cookbook.data.network

import com.hfad.cookbook.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesService {

    @GET("recipes/random")
    suspend fun getPopularRecipes(
        @Query("number") number: String,
        @Query("tags") tags: String?,
        @Query("apiKey") apiKey: String = BuildConfig.SPOONACULAR_API_KEY
    ): NetworkRecipeContainer
}

object RecipesApi {

    private const val BASE_URL = "https://api.spoonacular.com/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val recipeService = retrofit.create(RecipesService::class.java)
}
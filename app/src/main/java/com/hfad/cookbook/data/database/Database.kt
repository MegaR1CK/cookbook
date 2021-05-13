package com.hfad.cookbook.data.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CachedRecipesDao {

    @Query("select * from cached_recipe_cards")
    fun getRecipeCards(): LiveData<List<DatabaseRecipeCard>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipeCards(vararg recipes: DatabaseRecipeCard)
}

@Database(entities = [DatabaseRecipeCard::class], version = 1)
abstract class RecipesDatabase : RoomDatabase() {
    abstract val cachedRecipeCardsDao: CachedRecipesDao

    companion object {
        private lateinit var INSTANCE: RecipesDatabase

        fun getDatabase(context: Context): RecipesDatabase {
            synchronized(RecipesDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        RecipesDatabase::class.java,
                        "recipes"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}
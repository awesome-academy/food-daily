package com.sunasterisk.fooddaily.data.repository

import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.data.source.OnLoadedCallback
import com.sunasterisk.fooddaily.data.source.RecipeDataSource
import com.sunasterisk.fooddaily.data.source.remote.RecipeRemoteDataSource

class RecipeRepository private constructor(
    private val remoteDataSource: RecipeDataSource.Remote
): RecipeDataSource.Remote {
    override fun getRandomRecipes(callback: OnLoadedCallback<FoodDetail>) {
        remoteDataSource.getRandomRecipes(callback)
    }

    companion object {
        private var instance: RecipeRepository? = null
        fun getInstance(remoteDataSource: RecipeRemoteDataSource): RecipeRepository =
            instance ?: RecipeRepository(remoteDataSource).also { instance = it }
    }
}

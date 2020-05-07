package com.sunasterisk.fooddaily.data.repository

import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.data.source.OnLoadedCallback
import com.sunasterisk.fooddaily.data.source.RecipeDataSource
import com.sunasterisk.fooddaily.data.source.remote.RecipeRemoteDataSource

class RecipeRepository(
    private val remoteDataSource: RecipeRemoteDataSource
): RecipeDataSource.Remote {
    override fun getRandomRecipes(url: String, callback: OnLoadedCallback<FoodDetail>) {
        remoteDataSource.getRandomRecipes(url, callback)
    }

    companion object {
        private var instance: RecipeRepository? = null
        fun getInstance(remoteDataSource: RecipeRemoteDataSource): RecipeRepository =
            instance ?: RecipeRepository(remoteDataSource).also { instance = it }
    }
}

package com.sunasterisk.fooddaily.data.repository

import com.sunasterisk.fooddaily.data.source.OnDataLoadedCallback
import com.sunasterisk.fooddaily.data.source.RecipeDataSource
import com.sunasterisk.fooddaily.data.source.remote.RecipeRemoteDataSource

class RecipeRepository(
    private val remoteDataSource: RecipeRemoteDataSource
): RecipeDataSource.Remote {
    override fun getRandomRecipes(url: String, callback: OnDataLoadedCallback) {
        remoteDataSource.getRandomRecipes(url, callback)
    }
}

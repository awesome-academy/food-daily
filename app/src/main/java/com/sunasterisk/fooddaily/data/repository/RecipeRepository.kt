package com.sunasterisk.fooddaily.data.repository

import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.data.source.OnLoadedCallback
import com.sunasterisk.fooddaily.data.source.RecipeDataSource
import com.sunasterisk.fooddaily.data.source.local.RecipeLocalDataSource
import com.sunasterisk.fooddaily.data.source.remote.RecipeRemoteDataSource

class RecipeRepository private constructor(
    private val remoteDataSource: RecipeDataSource.Remote,
    private val localDataSource: RecipeDataSource.Local
): RecipeDataSource.Remote, RecipeDataSource.Local {

    override fun getRandomRecipes(callback: OnLoadedCallback<List<FoodDetail>>) {
        remoteDataSource.getRandomRecipes(callback)
    }

    override fun getAllFavoriteFoods(callback: OnLoadedCallback<List<String>>) {
        localDataSource.getAllFavoriteFoods(callback)
    }

    override fun getAllPartyFoods(callback: OnLoadedCallback<List<String>>) {
        localDataSource.getAllPartyFoods(callback)
    }

    override fun getAllFamilyFoods(callback: OnLoadedCallback<List<String>>) {
        localDataSource.getAllFamilyFoods(callback)
    }

    companion object {
        private var instance: RecipeRepository? = null
        fun getInstance(
            remoteDataSource: RecipeDataSource.Remote,
            localDataSource: RecipeDataSource.Local
        ): RecipeRepository =
            instance ?:
            RecipeRepository(
                remoteDataSource,
                localDataSource
            ).also { instance = it }
    }
}

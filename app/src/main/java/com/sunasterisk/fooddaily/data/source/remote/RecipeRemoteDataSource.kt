package com.sunasterisk.fooddaily.data.source.remote

import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.data.source.OnLoadedCallback
import com.sunasterisk.fooddaily.data.source.RecipeDataSource
import com.sunasterisk.fooddaily.data.source.remote.response.GetResponsesAsync
import com.sunasterisk.fooddaily.data.source.remote.response.RecipeResponseHandler

class RecipeRemoteDataSource: RecipeDataSource.Remote {
    override fun getRandomRecipes(url: String, callback: OnLoadedCallback<FoodDetail>) {
        GetResponsesAsync(RecipeResponseHandler(), callback).execute(url)
    }

    companion object {
        private var instance: RecipeRemoteDataSource? = null
        fun getInstance(): RecipeRemoteDataSource =
            instance ?: RecipeRemoteDataSource().also { instance = it }
    }
}

package com.sunasterisk.fooddaily.data.source.remote

import com.sunasterisk.fooddaily.data.source.OnDataLoadedCallback
import com.sunasterisk.fooddaily.data.source.RecipeDataSource
import com.sunasterisk.fooddaily.data.source.remote.response.GetResponsesAsync
import com.sunasterisk.fooddaily.data.source.remote.response.RecipeResponseHandler

class RecipeRemoteDataSource: RecipeDataSource.Remote {
    override fun getRandomRecipes(url: String, callback: OnDataLoadedCallback) {
        GetResponsesAsync(RecipeResponseHandler(), callback).execute(url)
    }
}

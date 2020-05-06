package com.sunasterisk.fooddaily.data.source

import com.sunasterisk.fooddaily.data.model.FoodDetails

interface RecipeDataSource {
    interface Remote {
        fun getRandomRecipes(url: String, callback: OnDataLoadedCallback)
    }
    interface Local {

    }
}

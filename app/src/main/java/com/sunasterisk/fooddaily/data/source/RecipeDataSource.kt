package com.sunasterisk.fooddaily.data.source

import com.sunasterisk.fooddaily.data.model.FoodDetail

interface RecipeDataSource {
    interface Remote {
        fun getRandomRecipes(callback: OnLoadedCallback<FoodDetail>)
    }
    interface Local {

    }
}

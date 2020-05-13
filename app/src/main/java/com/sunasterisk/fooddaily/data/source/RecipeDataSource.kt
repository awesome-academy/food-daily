package com.sunasterisk.fooddaily.data.source

import com.sunasterisk.fooddaily.data.model.FoodDetail

interface RecipeDataSource {
    interface Remote {
        fun getRandomRecipes(callback: OnLoadedCallback<List<FoodDetail>>)
    }
    interface Local {
        fun getAllFavoriteFoods(callback: OnLoadedCallback<List<String>>)
        fun getAllPartyFoods(callback: OnLoadedCallback<List<String>>)
        fun getAllFamilyFoods(callback: OnLoadedCallback<List<String>>)
    }
}

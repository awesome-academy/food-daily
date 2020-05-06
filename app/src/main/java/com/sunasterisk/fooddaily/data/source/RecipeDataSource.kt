package com.sunasterisk.fooddaily.data.source

interface RecipeDataSource {
    interface Remote {
        fun getRandomRecipes(url: String, callback: OnDataLoadedCallback)
    }
    interface Local {

    }
}

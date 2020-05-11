package com.sunasterisk.fooddaily.data.source

import com.sunasterisk.fooddaily.data.model.FoodDetail

interface SearchDataSource {
    interface Remote {
        fun searchRecipe(keyword: String, callback: OnLoadedCallback<FoodDetail>)
    }
}

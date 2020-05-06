package com.sunasterisk.fooddaily.data.source

interface SearchDataSource {
    interface Remote {
        fun searchRecipe(urlRequest: String, callback: OnDataLoadedCallback)
    }
}

package com.sunasterisk.fooddaily.data.repository

import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.data.source.OnLoadedCallback
import com.sunasterisk.fooddaily.data.source.SearchDataSource
import com.sunasterisk.fooddaily.data.source.remote.SearchRemoteDataSource

class SearchRepository private constructor(
    private val searchDataSource: SearchDataSource.Remote
): SearchDataSource.Remote {
    override fun searchRecipe(keyword: String, callback: OnLoadedCallback<FoodDetail>) {
        searchDataSource.searchRecipe(keyword, callback)
    }

    companion object {
        private var instance: SearchRepository? = null
        fun getInstance(searchRemoteDataSource: SearchRemoteDataSource): SearchRepository =
            instance ?: SearchRepository(searchRemoteDataSource).also { instance = it }
    }
}

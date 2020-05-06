package com.sunasterisk.fooddaily.data.repository

import com.sunasterisk.fooddaily.data.source.OnDataLoadedCallback
import com.sunasterisk.fooddaily.data.source.SearchDataSource
import com.sunasterisk.fooddaily.data.source.remote.SearchRemoteDataSource

class SearchRepository(
    private val searchRemoteSource: SearchRemoteDataSource
): SearchDataSource.Remote {
    override fun searchRecipe(urlRequest: String, callback: OnDataLoadedCallback) {
        searchRemoteSource.searchRecipe(urlRequest, callback)
    }
}

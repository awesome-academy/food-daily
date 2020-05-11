package com.sunasterisk.fooddaily.data.source.remote

import com.sunasterisk.fooddaily.BuildConfig
import com.sunasterisk.fooddaily.data.model.DataRequest
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.data.source.OnLoadedCallback
import com.sunasterisk.fooddaily.data.source.SearchDataSource
import com.sunasterisk.fooddaily.data.source.remote.response.GetResponsesAsync
import com.sunasterisk.fooddaily.data.source.remote.response.SearchResponseHandler
import com.sunasterisk.fooddaily.utils.ApiKeys

class SearchRemoteDataSource private constructor() : SearchDataSource.Remote {
    override fun searchRecipe(keyword: String, callback: OnLoadedCallback<FoodDetail>) {

        val urlRequest = DataRequest(
            paths = listOf(
                ApiKeys.PATH_RECIPES,
                ApiKeys.PATH_COMPLEX_SEARCH
            ),
            queryParams = mapOf(
                ApiKeys.QUERY_KEYWORD to keyword,
                ApiKeys.QUERY_NUMBER to ApiKeys.DEFAULT_SEARCH_RESULT_LIMIT,
                ApiKeys.QUERY_API_KEY to BuildConfig.API_KEY
            )
        ).toUrl()
        GetResponsesAsync(SearchResponseHandler.getInstance(), callback).execute(urlRequest)
    }

    companion object {
        private var instance: SearchRemoteDataSource? = null
        fun getInstance(): SearchRemoteDataSource =
            instance ?: SearchRemoteDataSource().also { instance = it }
    }
}

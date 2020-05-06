package com.sunasterisk.fooddaily.data.source.remote

import com.sunasterisk.fooddaily.data.source.OnDataLoadedCallback
import com.sunasterisk.fooddaily.data.source.SearchDataSource
import com.sunasterisk.fooddaily.data.source.remote.response.GetResponsesAsync
import com.sunasterisk.fooddaily.data.source.remote.response.SearchResponseHandler

class SearchRemoteDataSource: SearchDataSource.Remote {
    override fun searchRecipe(urlRequest: String, callback: OnDataLoadedCallback) {
        GetResponsesAsync(SearchResponseHandler(), callback).execute(urlRequest)
    }
}

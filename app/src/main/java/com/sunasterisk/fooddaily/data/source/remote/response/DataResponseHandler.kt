package com.sunasterisk.fooddaily.data.source.remote.response

import com.sunasterisk.fooddaily.data.model.FoodDetail
import java.net.URL

interface DataResponseHandler {
    fun getResponse(urlRequest: String): List<FoodDetail>
}

package com.sunasterisk.fooddaily.data.source.remote.response

import com.sunasterisk.fooddaily.data.model.FoodDetails
import java.net.URL

interface DataResponseHandler {
    fun getResponse(urlRequest: String): List<FoodDetails>
    fun handleConnect(url: URL): String
    fun convertJSONToFood(strJSON: String): List<FoodDetails>
}

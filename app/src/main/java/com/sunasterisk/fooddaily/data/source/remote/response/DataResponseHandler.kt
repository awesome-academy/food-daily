package com.sunasterisk.fooddaily.data.source.remote.response

import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.utils.Constants
import org.json.JSONObject

interface DataResponseHandler {
    fun getResponse(urlRequest: String): List<FoodDetail>
    fun convertJSONToFood(strJSON: String): List<FoodDetail> {
        val foodDetails = mutableListOf<FoodDetail>()
        val recipes = JSONObject(strJSON).getJSONArray(Constants.RESULTS)
        for (i in 0 until recipes.length()) {
            val food = FoodDetail(recipes.getJSONObject(i))
            foodDetails.add(food)
        }
        return foodDetails
    }
}

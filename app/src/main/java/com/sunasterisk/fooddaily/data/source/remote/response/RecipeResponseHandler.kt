package com.sunasterisk.fooddaily.data.source.remote.response

import com.sunasterisk.fooddaily.data.model.FoodDetail
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class RecipeResponseHandler : DataResponseHandler {
    override fun getResponse(urlRequest: String): List<FoodDetail> {
        // request to api get data is String type
        val strJSON = handleConnect(URL(urlRequest))
        return convertJSONToFood(strJSON)
    }

    private fun handleConnect(url: URL): String {
        var result = ""
        try {
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.apply {
                doInput = true
                requestMethod = "GET"
                connect()
            }
            val responseCode = urlConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val scanner = Scanner(url.openStream())
                while (scanner.hasNext()) {
                    result += scanner.nextLine()
                }
                scanner.close()
            }
        } catch (e: Exception) {
        }
        return result
    }

    private fun convertJSONToFood(strJSON: String): List<FoodDetail> {
        val foodList = ArrayList<FoodDetail>()
        try {
            val jsonObj = JSONObject(strJSON)
            val recipes = jsonObj.getJSONArray("recipes")
            // looping through all recipes
            for (i in 0 until recipes.length()) {
                val recipe = recipes.getJSONObject(i)
                val foodDetail = FoodDetail(recipe)
                foodList.add(foodDetail)
            }
        } catch (exception: Exception) {
        }
        return foodList
    }
}

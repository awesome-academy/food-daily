package com.sunasterisk.fooddaily.data.source.remote.response

import com.sunasterisk.fooddaily.data.model.FoodDetails
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class SearchResponseHandler : DataResponseHandler {
    override fun getResponse(urlRequest: String): List<FoodDetails> {
        val strJSON = handleConnect(URL(urlRequest))
        return convertJSONToFood(strJSON)
    }

    override fun handleConnect(url: URL): String {
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

    override fun convertJSONToFood(strJSON: String): List<FoodDetails> {
        val foods = ArrayList<FoodDetails>()
        val jsonObject = JSONObject(strJSON)
        val recipes = jsonObject.getJSONArray("results")
        for (i in 0 until recipes.length()) {
            val recipe = recipes.getJSONObject(i)
            val id = recipe.getInt("id")
            val title = recipe.getString("title")
            val image = recipe.getString("image")
            val food = FoodDetails(
                id, title, image,
                null, null, null, null, null
            )
            foods.add(food)
        }
        return foods
    }
}

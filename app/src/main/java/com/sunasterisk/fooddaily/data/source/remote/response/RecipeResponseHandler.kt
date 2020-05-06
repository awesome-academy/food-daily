package com.sunasterisk.fooddaily.data.source.remote.response

import com.sunasterisk.fooddaily.data.model.FoodDetails
import com.sunasterisk.fooddaily.data.model.Ingredients
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class RecipeResponseHandler : DataResponseHandler {
    override fun getResponse(urlRequest: String): List<FoodDetails> {
        // request đến api lấy data là một chuỗi String
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
        val foodList = ArrayList<FoodDetails>()
        try {
            val jsonObj = JSONObject(strJSON)
            val recipes = jsonObj.getJSONArray("recipes")
            // looping through all recipes
            for (i in 0 until recipes.length()) {
                val recipe = recipes.getJSONObject(i)
                val id = recipe.getInt("id")
                val image = recipe.getString("image")
                val ingredients = analyzedIngredients(
                    recipe.getJSONArray("extendedIngredients")
                )
                val instructions = analyzedInstructions(
                    recipe.getJSONArray("analyzedInstructions")
                )
                val price = recipe.getString("pricePerServing")
                val readyInMinutes = recipe.getInt("readyInMinutes")
                val summary = standardizeSummary(recipe.getString("summary"))
                val title = recipe.getString("title")

                val foodDetails = FoodDetails(
                    id, title, image, price, readyInMinutes, summary, ingredients, instructions
                )
                foodList.add(foodDetails)
            }
        } catch (exception: Exception) {
        }
        return foodList
    }

    // chuẩn hóa tóm tắt món ăn, loại bỏ phần đuôi quảng cáo
    private fun standardizeSummary(summary: String): String {
        var result = ""
        val k = summary.indexOf(" Try")
        result = summary.substring(0, k)
        result = result.replace("<b>", "")
        result = result.replace("</b>", "")
        result = result.trim()
        return result
    }

    // phân tích công thức thành từng bước lưu thành chuỗi
    private fun analyzedInstructions(instructions: JSONArray): List<String> {
        val stepList = ArrayList<String>()
        val instruction = instructions.getJSONObject(0)
        val steps = instruction.getJSONArray("steps")
        for (i in 0 until steps.length()) {
            val stepItem = steps.getJSONObject(i)
            val step = stepItem.getString("step")
            stepList.add(step)
        }
        return stepList
    }

    // phân tích các thành phần sử dụng, lưu thành chuỗi
    private fun analyzedIngredients(extendedIngredients: JSONArray): List<Ingredients> {
        val ingredients = ArrayList<Ingredients>()
        for (i in 0 until extendedIngredients.length()) {
            val jsonObject = extendedIngredients.getJSONObject(i)
            val id = jsonObject.getInt("id")
            val name = jsonObject.getString("name")
            val original = jsonObject.getString("original")
            val ingredient = Ingredients(id, name, original)
            ingredients.add(ingredient)
        }
        return ingredients
    }
}

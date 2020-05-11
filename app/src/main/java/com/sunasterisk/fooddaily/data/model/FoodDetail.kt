package com.sunasterisk.fooddaily.data.model

import org.json.JSONObject

private const val JSON_KEY_ID = "id"
private const val JSON_KEY_TITLE = "title"
private const val JSON_KEY_PRICE = "pricePerServing"
private const val JSON_KEY_READY_MINUTES = "readyInMinutes"
private const val JSON_KEY_SUMMARY = "summary"
private const val JSON_KEY_IMAGE = "image"
private const val JSON_KEY_INGREDIENTS = "extendedIngredients"
private const val JSON_KEY_INSTRUCTIONS = "analyzedInstructions"
private const val JSON_KEY_NAME = "name"
private const val JSON_KEY_ORIGINAL = "original"
private const val JSON_KEY_STEP = "step"
private const val JSON_KEY_STEPS = "steps"

data class FoodDetail(
    var id: Int = 0,
    var title: String = "",
    var price: String? = null,
    var readyMinutes: Int? = null,
    var summary: String? = null,
    var imageUrl: String = "",
    var ingredients: List<Ingredient>? = null,
    var instructions: List<String>? = null
) {
    constructor(jsonObject: JSONObject) : this(
        id = jsonObject.optInt(JSON_KEY_ID),
        title = jsonObject.optString(JSON_KEY_TITLE),
        price = jsonObject.optString(JSON_KEY_PRICE),
        readyMinutes = jsonObject.optInt(JSON_KEY_READY_MINUTES),
        summary = jsonObject.optString(JSON_KEY_SUMMARY),
        imageUrl = jsonObject.optString(JSON_KEY_IMAGE),
        ingredients = ArrayList<Ingredient>().apply {
            val ingredientList = jsonObject.optJSONArray(JSON_KEY_INGREDIENTS)
            for (i in 0 until ingredientList.length()) {
                val item = ingredientList.getJSONObject(i)
                val id = item.getInt(JSON_KEY_ID)
                val name = item.getString(JSON_KEY_NAME)
                val original = item.getString(JSON_KEY_ORIGINAL)
                val ingredient = Ingredient(id, name, original)
                add(ingredient)
            }
        },
        instructions = ArrayList<String>().apply {
            val instructionList = jsonObject.optJSONArray(JSON_KEY_INSTRUCTIONS)
            val instruction = instructionList.optJSONObject(0)
            val stepList = instruction.optJSONArray(JSON_KEY_STEPS)
            for (i in 0 until stepList.length()) {
                val stepItem = stepList.getJSONObject(i)
                val step = stepItem.getString(JSON_KEY_STEP)
                add(step)
            }
        }
    )
}

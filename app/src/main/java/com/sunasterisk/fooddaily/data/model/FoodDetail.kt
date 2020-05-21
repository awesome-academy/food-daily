package com.sunasterisk.fooddaily.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
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

@Parcelize
data class FoodDetail(
    var id: String? = "",
    var title: String? = "",
    var price: String? = "",
    var readyMinutes: String? = "",
    var summary: String? = "",
    var imageUrl: String? = "",
    var ingredients: List<Ingredient>? = null,
    var instructions: List<String>? = null
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        id = jsonObject.optString(JSON_KEY_ID),
        title = jsonObject.optString(JSON_KEY_TITLE),
        price = jsonObject.optString(JSON_KEY_PRICE),
        readyMinutes = jsonObject.optString(JSON_KEY_READY_MINUTES),
        summary = jsonObject.optString(JSON_KEY_SUMMARY),
        imageUrl = jsonObject.optString(JSON_KEY_IMAGE),
        ingredients = mutableListOf<Ingredient>().apply {
            val ingredientList = jsonObject.optJSONArray(JSON_KEY_INGREDIENTS)
            for (i in 0 until ingredientList.length()) {
                val item = ingredientList.optJSONObject(i)
                val id = item.optString(JSON_KEY_ID)
                val name = item.optString(JSON_KEY_NAME)
                val original = item.optString(JSON_KEY_ORIGINAL)
                val ingredient = Ingredient(id, name, original)
                add(ingredient)
            }
        },
        instructions = mutableListOf<String>().apply {
            val instructionList = jsonObject.optJSONArray(JSON_KEY_INSTRUCTIONS)
            val instruction = instructionList.optJSONObject(0)
            val stepList = instruction.optJSONArray(JSON_KEY_STEPS)
            for (i in 0 until stepList.length()) {
                val stepItem = stepList.optJSONObject(i)
                val step = stepItem.optString(JSON_KEY_STEP)
                add(step)
            }
        }
    )
}

package com.sunasterisk.fooddaily.data.model

data class FoodDetails(
    val id: Int,
    val title: String,
    val price: String,
    val readyMinutes: Int,
    val summary: String,
    val imageUrl: String,
    val ingredients: Ingredients,
    val instructions: List<String>
)

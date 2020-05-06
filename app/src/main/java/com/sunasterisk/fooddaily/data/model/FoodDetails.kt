package com.sunasterisk.fooddaily.data.model

data class FoodDetails(
    val id: Int,
    val title: String,
    val price: Float,
    val readyMinutes: Int,
    val summary: String,
    val imageUrl: String,
    val ingredients: List<Ingredients>,
    val instructions: List<String>
)

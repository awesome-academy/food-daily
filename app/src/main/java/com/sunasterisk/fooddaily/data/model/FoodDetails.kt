package com.sunasterisk.fooddaily.data.model

data class FoodDetails(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val price: String?,
    val readyMinutes: Int?,
    val summary: String?,
    val ingredients: List<Ingredients>?,
    val instructions: List<String>?
)

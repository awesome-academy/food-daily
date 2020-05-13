package com.sunasterisk.fooddaily.data.source.local.dao

import com.sunasterisk.fooddaily.data.model.FoodDetail

interface FavoriteFoodDAO {
    fun getAllFavoriteFoods(): List<String>
    fun insertFoodFavorite(foodDetail: FoodDetail)
    fun deleteFoodFavorite(foodDetail: FoodDetail)
}

package com.sunasterisk.fooddaily.data.source.local.dao

import com.sunasterisk.fooddaily.data.model.FoodDetail

interface FoodDAO {
    fun getAllFavoriteFoods(): List<FoodDetail>
    fun getAllFamilyFoods(): List<FoodDetail>
    fun getAllPartyFoods(): List<FoodDetail>

    fun insertFoodFavorite(foodDetail: FoodDetail)
    fun insertFoodFamily(foodDetail: FoodDetail)
    fun insertFoodParty(foodDetail: FoodDetail)

    fun deleteFoodFamily(foodDetail: FoodDetail)
    fun deleteFoodFavorite(foodDetail: FoodDetail)
    fun deleteFoodParty(foodDetail: FoodDetail)
}

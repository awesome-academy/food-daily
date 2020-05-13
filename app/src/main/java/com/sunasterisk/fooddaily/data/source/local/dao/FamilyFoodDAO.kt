package com.sunasterisk.fooddaily.data.source.local.dao

import com.sunasterisk.fooddaily.data.model.FoodDetail

interface FamilyFoodDAO {
    fun getAllFamilyFoods(): List<String>
    fun insertFoodFamily(foodDetail: FoodDetail)
    fun deleteFoodFamily(foodDetail: FoodDetail)
}

package com.sunasterisk.fooddaily.data.source.local.dao

import com.sunasterisk.fooddaily.data.model.FoodDetail

interface PartyFoodDAO {
    fun getAllPartyFoods(): List<String>
    fun insertFoodParty(foodDetail: FoodDetail)
    fun deleteFoodParty(foodDetail: FoodDetail)
}

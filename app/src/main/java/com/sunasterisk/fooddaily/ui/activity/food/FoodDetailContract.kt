package com.sunasterisk.fooddaily.ui.activity.food

import com.sunasterisk.fooddaily.data.model.FoodDetail

interface FoodDetailContract {
    interface Presenter {
        fun addToFavorite(food: FoodDetail)
        fun addToFamily(food: FoodDetail)
        fun addToParty(food: FoodDetail)
    }
}

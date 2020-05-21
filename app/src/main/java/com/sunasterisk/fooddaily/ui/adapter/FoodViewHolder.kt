package com.sunasterisk.fooddaily.ui.adapter

import android.view.View
import com.sunasterisk.fooddaily.data.model.FoodDetail

class DailyMenuViewHolder(view: View) : FoodAdapter.FoodViewHolder(view) {
    override fun bind(food: FoodDetail) {
        //TODO bind data from foodList to DailyMenu
    }
}

class OtherFoodListViewHolder(view: View) : FoodAdapter.FoodViewHolder(view) {
    override fun bind(food: FoodDetail) {
        //TODO bind data from foodList to OtherFoodList
    }
}

class OtherFoodGridViewHolder(view: View) : FoodAdapter.FoodViewHolder(view) {
    override fun bind(food: FoodDetail) {
        //TODO bind data from foodList to OtherFoodGrid
    }
}

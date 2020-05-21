package com.sunasterisk.fooddaily.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sunasterisk.fooddaily.data.model.FoodDetail

abstract class FoodViewHolder(view: View): RecyclerView.ViewHolder(view) {
    abstract fun bind(food: FoodDetail)
}

class DailyMenuViewHolder(view: View) : FoodViewHolder(view) {
    override fun bind(food: FoodDetail) {
        //TODO bind data from foodList to DailyMenu
    }
}

class OtherFoodListViewHolder(view: View) : FoodViewHolder(view) {
    override fun bind(food: FoodDetail) {
        //TODO bind data from foodList to OtherFoodList
    }
}

class OtherFoodGridViewHolder(view: View) : FoodViewHolder(view) {
    override fun bind(food: FoodDetail) {
        //TODO bind data from foodList to OtherFoodGrid
    }
}

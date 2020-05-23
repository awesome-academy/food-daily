package com.sunasterisk.fooddaily.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.utils.applyGlide
import kotlinx.android.synthetic.main.item_daily_menu_food.view.*
import kotlinx.android.synthetic.main.item_other_food_grid.view.*
import kotlinx.android.synthetic.main.item_other_food_list.view.*

abstract class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(food: FoodDetail)
}

class DailyMenuViewHolder(view: View) : FoodViewHolder(view) {
    @SuppressLint("SetTextI18n")
    override fun bind(food: FoodDetail) {
        itemView.apply {
            textFoodNameDailyMenu.text = food.title
            textDailyMenuPrice.text = food.price
            textDailyMenuReadyMinutes.text =
                food.readyMinutes + resources.getString(R.string.value_time_required)
            imageFoodDailyMenu.applyGlide(context, food.imageUrl)
        }
    }
}

class OtherFoodListViewHolder(view: View) : FoodViewHolder(view) {
    override fun bind(food: FoodDetail) {
        itemView.apply {
            textFoodNameOtherList.text = food.title
            textFoodNameOtherList.isSelected = true
            textOtherListPrice.text = food.price
            textOtherListReadyMinutes.text = food.readyMinutes
            imageFoodOtherList.applyGlide(context, food.imageUrl)
        }
    }
}

class OtherFoodGridViewHolder(view: View) : FoodViewHolder(view) {
    override fun bind(food: FoodDetail) {
        itemView.apply {
            textFoodOtherGrid.text = food.title
            textOtherGridPrice.text = food.price
            textOtherGridReadyMinutes.text = food.readyMinutes
            imageFoodOtherGrid.applyGlide(context, food.imageUrl)
        }
    }
}

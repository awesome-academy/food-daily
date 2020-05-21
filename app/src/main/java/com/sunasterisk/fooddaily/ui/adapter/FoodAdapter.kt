package com.sunasterisk.fooddaily.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.utils.Constants

class FoodAdapter(
    private val foodList: List<FoodDetail>,
    private val viewType: Int
) : RecyclerView.Adapter<FoodViewHolder>() {

    override fun getItemViewType(position: Int): Int = viewType
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return when (viewType) {
            Constants.FOOD_TYPE_DAILY_MENU -> {
                DailyMenuViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_daily_menu_food, parent, false)
                )
            }
            Constants.FOOD_TYPE_OTHER_LIST -> {
                OtherFoodListViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_other_food_list, parent, false)
                )
            }
            else -> OtherFoodGridViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_other_food_grid, parent, false)
            )
        }
    }

    override fun getItemCount(): Int = foodList.size

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(foodList[position])
    }
}

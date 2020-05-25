package com.sunasterisk.fooddaily.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.utils.FoodType

class FoodAdapter(
    private val foodList: List<FoodDetail>,
    private val viewType: Int,
    private val onClickListener: (FoodDetail) -> Unit
) : RecyclerView.Adapter<FoodViewHolder>() {

    override fun getItemViewType(position: Int): Int = viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return when (viewType) {
            FoodType.FOOD_TYPE_DAILY_MENU -> {
                DailyMenuViewHolder(
                    inflateView(parent.context, R.layout.item_daily_menu_food, parent),
                    onClickListener
                )
            }
            FoodType.FOOD_TYPE_OTHER_LIST -> {
                OtherFoodListViewHolder(
                    inflateView(parent.context, R.layout.item_other_food_list, parent),
                    onClickListener
                )
            }
            else -> OtherFoodGridViewHolder(
                inflateView(parent.context, R.layout.item_other_food_grid, parent),
                onClickListener
            )
        }
    }

    override fun getItemCount(): Int = foodList.size

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(foodList[position])
    }

    private fun inflateView(context: Context, layoutRes: Int, parent: ViewGroup): View =
        LayoutInflater.from(context).inflate(layoutRes, parent, false)
}

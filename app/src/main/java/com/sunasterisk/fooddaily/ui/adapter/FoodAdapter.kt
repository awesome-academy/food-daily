package com.sunasterisk.fooddaily.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.utils.Constants
import kotlinx.android.synthetic.main.item_daily_menu_food.view.*
import kotlinx.android.synthetic.main.item_other_food_grid.view.*
import kotlinx.android.synthetic.main.item_other_food_list.view.*

class FoodAdapter(
    private val foodList: List<FoodDetail>,
    private val viewType: Int
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class DailyMenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(food: FoodDetail) {
            //TODO bind data from foodList to DailyMenu
            itemView.textFoodNameDailyMenu.text = food.title
            itemView.textDailyMenuPrice.text = food.price
            itemView.textDailyMenuReadyMinutes.text = food.readyMinutes
            val requestOptions = RequestOptions().apply {
                transform(CenterCrop(), RoundedCorners(16))
            }
            Glide.with(itemView.context)
                .load(food.imageUrl)
                .apply(requestOptions)
                .placeholder(R.drawable.ic_loading_spinner)
                .into(itemView.imageFoodDailyMenu)
        }
    }

    class OtherFoodListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(food: FoodDetail) {
            //TODO bind data from foodList to OtherFoodList
            itemView.textFoodNameOtherList.text = food.title
            itemView.textOtherListPrice.text = food.price
            itemView.textOtherListReadyMinutes.text = food.readyMinutes
            val requestOptions = RequestOptions().apply {
                transform(CenterCrop(), RoundedCorners(16))
            }
            Glide.with(itemView.context)
                .load(food.imageUrl)
                .apply(requestOptions)
                .placeholder(R.drawable.ic_loading_spinner)
                .into(itemView.imageFoodOtherList)
        }
    }

    class OtherFoodGridViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(food: FoodDetail) {
            //TODO bind data from foodList to OtherFoodGrid
            itemView.textFoodOtherGrid.text = food.title
            val requestOptions = RequestOptions().apply {
                transform(CenterCrop(), RoundedCorners(16))
            }
            Glide.with(itemView.context)
                .load(food.imageUrl)
                .apply(requestOptions)
                .placeholder(R.drawable.ic_loading_spinner)
                .into(itemView.imageFoodOtherGrid)
        }
    }

    override fun getItemViewType(position: Int): Int = viewType
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DailyMenuViewHolder -> holder.bind(foodList[position])
            is OtherFoodListViewHolder -> holder.bind(foodList[position])
            is OtherFoodGridViewHolder -> holder.bind(foodList[position])
        }
    }
}

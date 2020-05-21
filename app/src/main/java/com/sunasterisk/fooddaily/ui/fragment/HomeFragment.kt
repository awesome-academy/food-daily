package com.sunasterisk.fooddaily.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.ui.adapter.FoodAdapter
import com.sunasterisk.fooddaily.utils.Constants
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), View.OnClickListener {

    private var isSortList: Boolean = false
    private var foodList = mutableListOf<FoodDetail>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        displayFoodOnDailyMenu()
        displayFoodOnOtherFood()

        buttonSortOtherFood.setOnClickListener(this)
    }

    private fun displayFoodOnOtherFood() {
        recyclerViewOtherFood.adapter = FoodAdapter(foodList, Constants.FOOD_TYPE_OTHER_LIST)
        recyclerViewOtherFood.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        isSortList = true
        recyclerViewOtherFood.setHasFixedSize(false)
    }

    private fun displayFoodOnDailyMenu() {
        recyclerViewDailyMenu.adapter = FoodAdapter(foodList, Constants.FOOD_TYPE_DAILY_MENU)
        recyclerViewDailyMenu.setHasFixedSize(false)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonSortOtherFood -> {
                handleDisplayOtherFood()
            }
        }
    }

    private fun handleDisplayOtherFood() {
        if (isSortList) {
            displayOtherFoodGrid()
        } else {
            displayOtherFoodList()
        }
    }

    private fun displayOtherFoodList() {
        recyclerViewOtherFood.adapter = FoodAdapter(foodList, Constants.FOOD_TYPE_OTHER_LIST)
        recyclerViewOtherFood.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        isSortList = true
    }

    private fun displayOtherFoodGrid() {
        recyclerViewOtherFood.adapter =
            FoodAdapter(foodList, Constants.FOOD_TYPE_OTHER_GRID)
        recyclerViewOtherFood.layoutManager =
            GridLayoutManager(context, 2)
        isSortList = false
    }
}

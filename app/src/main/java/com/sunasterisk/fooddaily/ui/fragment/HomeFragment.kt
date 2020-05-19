package com.sunasterisk.fooddaily.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.ui.adapter.FoodAdapter
import com.sunasterisk.fooddaily.ui.base.BaseFragment
import com.sunasterisk.fooddaily.ui.main.MainActivity
import com.sunasterisk.fooddaily.utils.Constants
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), View.OnClickListener {

    override val layoutRes: Int = R.layout.fragment_home

    private var isSortList: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        displayFoodOnDailyMenu()
        displayFoodOnOtherFood()

        buttonSortOtherFood.setOnClickListener(this)
    }

    private fun displayFoodOnOtherFood() {
        recyclerViewOtherFood.adapter = FoodAdapter(MainActivity.foodsAll, Constants.FOOD_TYPE_OTHER_LIST)
        recyclerViewOtherFood.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        isSortList = true
        recyclerViewOtherFood.setHasFixedSize(false)
    }

    private fun displayFoodOnDailyMenu() {
        recyclerViewDailyMenu.adapter = FoodAdapter(MainActivity.foodsAll, Constants.FOOD_TYPE_DAILY_MENU)
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
        recyclerViewOtherFood.adapter = FoodAdapter(MainActivity.foodsAll, Constants.FOOD_TYPE_OTHER_LIST)
        recyclerViewOtherFood.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        isSortList = true
    }

    private fun displayOtherFoodGrid() {
        recyclerViewOtherFood.adapter =
            FoodAdapter(MainActivity.foodsAll, Constants.FOOD_TYPE_OTHER_GRID)
        recyclerViewOtherFood.layoutManager =
            GridLayoutManager(context, 2)
        isSortList = false
    }
}

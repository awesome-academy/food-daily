package com.sunasterisk.fooddaily.ui.fragment.home

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.ui.adapter.FoodAdapter
import com.sunasterisk.fooddaily.ui.base.BaseFragment
import com.sunasterisk.fooddaily.utils.Constants
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

const val COLUMN_LIMIT = 2

class HomeFragment private constructor() : BaseFragment(), View.OnClickListener, HomeContract.View {

    override val layoutRes: Int = R.layout.fragment_home

    private val homePresenter = HomePresenter(this)
    private lateinit var otherFoods: List<FoodDetail>
    private var isSortList: Boolean = false

    override fun getArgument() {
        otherFoods =
            arguments?.getParcelableArrayList<FoodDetail>(Constants.EXTRA_FOOD_LIST) as ArrayList
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homePresenter.createDailyMenu(otherFoods)
        displayOtherFoods()
        buttonSortOtherFood.setOnClickListener(this)
    }

    private fun displayOtherFoods() {
        recyclerViewOtherFood.adapter =
            FoodAdapter(otherFoods, Constants.FOOD_TYPE_OTHER_LIST)
        recyclerViewOtherFood.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        isSortList = true
        recyclerViewOtherFood.setHasFixedSize(false)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonSortOtherFood -> {
                handleDisplayOtherFood()
            }
        }
    }

    private fun handleDisplayOtherFood() {
        val icon = if (isSortList) {
            displayOtherFoodGrid()
            R.drawable.ic_list
        } else {
            displayOtherFoodList()
            R.drawable.ic_grid
        }
        buttonSortOtherFood.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
    }

    private fun displayOtherFoodList() {
        recyclerViewOtherFood.adapter =
            FoodAdapter(otherFoods, Constants.FOOD_TYPE_OTHER_LIST)
        recyclerViewOtherFood.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        isSortList = true
    }

    private fun displayOtherFoodGrid() {
        recyclerViewOtherFood.adapter =
            FoodAdapter(otherFoods, Constants.FOOD_TYPE_OTHER_GRID)
        recyclerViewOtherFood.layoutManager = GridLayoutManager(context, COLUMN_LIMIT)
        isSortList = false
    }

    override fun showDailyMenu(dailyFoods: List<FoodDetail>) {
        recyclerViewDailyMenu.adapter =
            FoodAdapter(dailyFoods, Constants.FOOD_TYPE_DAILY_MENU)
        recyclerViewDailyMenu.setHasFixedSize(false)
    }

    companion object {
        private var instance: HomeFragment? = null
        fun getInstance(foods: List<FoodDetail>): HomeFragment =
            instance ?: HomeFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(
                        Constants.EXTRA_FOOD_LIST,
                        foods as ArrayList<out Parcelable>
                    )
                }
            }
    }
}


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
import com.sunasterisk.fooddaily.ui.fragment.food_detail.FoodDetailFragment
import com.sunasterisk.fooddaily.utils.Constants
import com.sunasterisk.fooddaily.utils.FoodType
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

const val COLUMN_LIMIT = 2

class HomeFragment: BaseFragment(), View.OnClickListener, HomeContract.View {

    override val layoutRes: Int = R.layout.fragment_home
    override val actionBarTitle: Int = R.string.title_home
    override val buttonBackActionBarVisibility: Int = View.INVISIBLE
    override val buttonSearchActionBarVisibility: Int = View.VISIBLE
    override val buttonCollectionActionBarVisibility: Int = View.GONE

    private val homePresenter = HomePresenter(this)
    private var otherFoods: List<FoodDetail>? = null
    private var isSortList: Boolean = false

    override fun getArgument() {
        otherFoods =
            arguments?.getParcelableArrayList<FoodDetail>(Constants.EXTRA_FOOD_LIST) as ArrayList
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        otherFoods?.let { homePresenter.createDailyMenu(it) }
        displayOtherFoodList()
        buttonSortOtherFood.setOnClickListener(this)
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
        recyclerViewOtherFood.apply {
            adapter = otherFoods?.let {
                FoodAdapter(it, FoodType.FOOD_TYPE_OTHER_LIST) { food ->
                    onFoodItemClick(food)
                }
            }
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(false)
        }
        isSortList = true
    }

    private fun displayOtherFoodGrid() {
        recyclerViewOtherFood.apply {
            adapter =
                otherFoods?.let {
                    FoodAdapter(it, FoodType.FOOD_TYPE_OTHER_GRID) { food ->
                        onFoodItemClick(food)
                    }
                }
            layoutManager = GridLayoutManager(context, COLUMN_LIMIT)
        }
        isSortList = false
    }

    override fun showDailyMenu(dailyFoods: List<FoodDetail>) {
        recyclerViewDailyMenu.apply {
            adapter =
                FoodAdapter(dailyFoods, FoodType.FOOD_TYPE_DAILY_MENU) { food ->
                    onFoodItemClick(food)
                }
            setHasFixedSize(false)
        }
    }

    private fun onFoodItemClick(food: FoodDetail) {
        switchFragment(FoodDetailFragment.newInstance(food))
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonSortOtherFood -> {
                handleDisplayOtherFood()
            }
        }
    }

    companion object {
        fun newInstance(foods: List<FoodDetail>): HomeFragment = HomeFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(
                        Constants.EXTRA_FOOD_LIST,
                        foods as ArrayList<out Parcelable>
                    )
                }
            }
    }
}

package com.sunasterisk.fooddaily.ui.fragment.food_detail

import android.os.Bundle
import android.view.View
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.ui.base.BaseFragment
import com.sunasterisk.fooddaily.utils.Constants

class FoodDetailFragment: BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_food_detail
    override val actionBarTitle: Int = R.string.title_food_detail
    override val buttonBackActionBarVisibility: Int = View.VISIBLE
    override val buttonSearchActionBarVisibility: Int = View.GONE
    override val buttonCollectionActionBarVisibility: Int = View.VISIBLE
    private var food: FoodDetail? = null

    override fun getArgument() {
        food = arguments?.getParcelable<FoodDetail>(Constants.EXTRA_FOOD_ITEM) as FoodDetail
    }

    companion object {
        fun newInstance(food: FoodDetail): FoodDetailFragment = FoodDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Constants.EXTRA_FOOD_ITEM, food)
                }
            }
    }
}

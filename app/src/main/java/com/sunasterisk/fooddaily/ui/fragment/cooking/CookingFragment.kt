package com.sunasterisk.fooddaily.ui.fragment.cooking

import android.view.View
import com.sunasterisk.fooddaily.ui.base.BaseFragment
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.ui.activity.food.FoodDetailActivity
import com.sunasterisk.fooddaily.ui.adapter.FoodAdapter
import com.sunasterisk.fooddaily.utils.FoodType
import kotlinx.android.synthetic.main.custom_action_bar.*
import kotlinx.android.synthetic.main.fragment_cooking.*

class CookingFragment: BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_cooking

    override fun initActionBar() {
        textActionBarTitle.text = getString(R.string.title_cooking)
        buttonBackActionBar.visibility = View.GONE
        buttonCollectionActionBar.visibility = View.GONE
        buttonSearchActionBar.visibility = View.GONE
    }

    private fun onClick(food: FoodDetail) {
        startActivityForResult(
            activity?.let { FoodDetailActivity.getIntent(it, food) },
            1
        )
    }

    override fun getArgument() {

    }

    override fun initPresenter() {

    }

    companion object {
        fun newInstance(): CookingFragment = CookingFragment()
    }
}

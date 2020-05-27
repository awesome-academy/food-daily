package com.sunasterisk.fooddaily.ui.main

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.ui.base.BaseActivity
import com.sunasterisk.fooddaily.ui.fragment.home.HomeFragment
import com.sunasterisk.fooddaily.ui.fragment.collection.CollectionFragment
import com.sunasterisk.fooddaily.ui.fragment.cooking.CookingFragment
import com.sunasterisk.fooddaily.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override val layoutRes: Int = R.layout.activity_main

    private lateinit var foodsAll: List<FoodDetail>
    private val bottomNavSelected = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                setFragment(HomeFragment.newInstance(foodsAll))
                true
            }
            R.id.navigation_collection -> {
                setFragment(CollectionFragment.newInstance())
                true
            }
            R.id.navigation_cooking -> {
                setFragment(CookingFragment.newInstance())
                true
            }
            else -> false
        }
    }

    override fun initView() {
        createFoodsAll()
        setFragment(HomeFragment.newInstance(foodsAll))
        initBottomNavigation()
    }

    override fun initPresenter() {}

    private fun createFoodsAll() {
        foodsAll = intent.getParcelableArrayListExtra(Constants.EXTRA_FOOD_LIST)
    }

    private fun initBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener(bottomNavSelected)
    }

    companion object {
        fun getIntent(context: Context, foods: List<FoodDetail>): Intent =
            Intent(context, MainActivity::class.java)
                .putParcelableArrayListExtra(
                    Constants.EXTRA_FOOD_LIST,
                    foods as ArrayList<out Parcelable>
                )
    }
}

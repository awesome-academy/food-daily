package com.sunasterisk.fooddaily.ui.main

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.ui.base.BaseActivity
import com.sunasterisk.fooddaily.ui.fragment.CollectionFragment
import com.sunasterisk.fooddaily.ui.fragment.CookingFragment
import com.sunasterisk.fooddaily.ui.fragment.HomeFragment
import com.sunasterisk.fooddaily.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : BaseActivity() {

    override val layoutRes: Int = R.layout.activity_main

    private val bottomNavSelected = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchHomeScreen()
                true
            }
            R.id.navigation_collection -> {
                switchCollectionScreen()
                true
            }
            R.id.navigation_cooking -> {
                switchCookingScreen()
                true
            }
            else -> false
        }
    }

    override fun initView() {
        createFoodsAll()
        createFoodsDaily()
        initFragment()
        initBottomNavigation()
    }

    private fun createFoodsAll() {
        foodsAll = intent.getParcelableArrayListExtra(Constants.KEY_FOOD_LIST)
    }

    private fun createFoodsDaily() {
        for (i in 0 until foodsAll.size) {
            if (i%3 == 0) {
                foodsDaily.add(foodsAll[i])
            }
        }
    }

    override fun initPresenter() {
    }

    private fun initBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener(bottomNavSelected)
    }

    private fun switchCookingScreen() {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameContent, CookingFragment())
        fragmentTransaction.commit()
    }

    private fun switchCollectionScreen() {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameContent, CollectionFragment())
        fragmentTransaction.commit()
    }

    private fun switchHomeScreen() {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameContent, HomeFragment())
        fragmentTransaction.commit()
    }

    private fun initFragment() {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frameContent, HomeFragment())
        fragmentTransaction.commit()
    }

    companion object {
        private lateinit var fragmentTransaction: FragmentTransaction
        lateinit var foodsAll: ArrayList<FoodDetail>
        lateinit var foodsDaily: ArrayList<FoodDetail>
    }
}

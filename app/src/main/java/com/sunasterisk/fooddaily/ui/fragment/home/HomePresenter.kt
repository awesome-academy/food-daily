package com.sunasterisk.fooddaily.ui.fragment.home

import com.sunasterisk.fooddaily.data.model.FoodDetail

class HomePresenter (private val view: HomeContract.View): HomeContract.Presenter {
    override fun createDailyMenu(otherFoods: List<FoodDetail>) {
        view.showDailyMenu(
            mutableListOf<FoodDetail>().apply {
                for (i in otherFoods.indices) if (i % 3 == 0) add(otherFoods[i])
            }
        )
    }
}

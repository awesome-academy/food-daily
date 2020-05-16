package com.sunasterisk.fooddaily.ui.splash

import com.sunasterisk.fooddaily.data.model.FoodDetail
import kotlin.Exception

interface SplashScreenContract {
    interface View {
        fun onTransportDataToHome(data: List<FoodDetail>)
        fun showError(exception: Exception)
    }

    interface Presenter {
        fun getRandomFoods()
    }
}

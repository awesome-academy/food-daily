package com.sunasterisk.fooddaily.ui.splash

import android.content.Intent
import android.os.Parcelable
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.data.repository.RecipeRepository
import com.sunasterisk.fooddaily.data.source.local.RecipeLocalDataSource
import com.sunasterisk.fooddaily.data.source.local.dao.FamilyFoodDAOImpl
import com.sunasterisk.fooddaily.data.source.local.dao.FavoriteFoodDAOImpl
import com.sunasterisk.fooddaily.data.source.local.dao.PartyFoodDAOImpl
import com.sunasterisk.fooddaily.data.source.local.database.FoodDailyDatabase
import com.sunasterisk.fooddaily.data.source.remote.RecipeRemoteDataSource
import com.sunasterisk.fooddaily.ui.base.BaseActivity
import com.sunasterisk.fooddaily.ui.main.MainActivity
import com.sunasterisk.fooddaily.utils.Constants
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.util.ArrayList

class SplashScreenActivity : BaseActivity(), SplashScreenContract.View {

    override val layoutRes: Int = R.layout.activity_splash_screen
    private var splashPresenter: SplashScreenPresenter? = null

    override fun initPresenter() {
        val foodDailyDatabase = FoodDailyDatabase.getInstance(applicationContext)
        val favoriteFoodDAO = FavoriteFoodDAOImpl.getInstance(foodDailyDatabase)
        val partyFoodDAO = PartyFoodDAOImpl.getInstance(foodDailyDatabase)
        val familyFoodDAO = FamilyFoodDAOImpl.getInstance(foodDailyDatabase)
        val recipeRepository = RecipeRepository.getInstance(
            RecipeRemoteDataSource.getInstance(),
            RecipeLocalDataSource.getInstance(favoriteFoodDAO, partyFoodDAO, familyFoodDAO)
        )
        splashPresenter = SplashScreenPresenter(this, recipeRepository)
        splashPresenter?.getRandomFoods()
    }

    override fun initView() {
        val animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate)
        imageLoadData.startAnimation(animRotate)
    }

    override fun onTransportDataToHome(data: List<FoodDetail>) {
        val intentMain = Intent(applicationContext, MainActivity::class.java).apply {
            putParcelableArrayListExtra (
                Constants.KEY_FOOD_LIST,
                data as ArrayList<out Parcelable>
            )
        }
        startActivity(intentMain)
    }

    override fun showError(exception: Exception) {
        Toast.makeText(
            this,
            getString(R.string.title_error) + exception.message,
            Toast.LENGTH_SHORT
        ).show()
        splashPresenter?.getRandomFoods()
    }
}

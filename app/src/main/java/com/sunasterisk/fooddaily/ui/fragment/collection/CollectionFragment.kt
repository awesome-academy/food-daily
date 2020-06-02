package com.sunasterisk.fooddaily.ui.fragment.collection

import com.sunasterisk.fooddaily.ui.base.BaseFragment
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.data.repository.RecipeRepository
import com.sunasterisk.fooddaily.data.source.local.RecipeLocalDataSource
import com.sunasterisk.fooddaily.data.source.local.dao.FoodDAOImpl
import com.sunasterisk.fooddaily.data.source.local.database.FoodDailyDatabase
import com.sunasterisk.fooddaily.data.source.remote.RecipeRemoteDataSource

class CollectionFragment: BaseFragment(), CollectionContract.View {

    override val layoutRes: Int = R.layout.fragment_collection

    private var collectionPresenter: CollectionPresenter? = null

    override fun initActionBar() {
        initPresenter()
    }

    override fun getArgument() {

    }

    private fun initPresenter() {
        val foodDailyDatabase = context?.let { FoodDailyDatabase.getInstance(it) }
        foodDailyDatabase?.let {
            val foodDAO = FoodDAOImpl.getInstance(it)
            val recipeRepository = RecipeRepository.getInstance(
                RecipeRemoteDataSource.getInstance(),
                RecipeLocalDataSource.getInstance(
                    foodDAO
                )
            )
            collectionPresenter = CollectionPresenter(this, recipeRepository)
        }
        collectionPresenter?.run {
            getAllFavoriteFoods()
            getAllFamilyFoods()
            getAllPartyFoods()
        }
    }

    override fun showFavoriteFoods(favoriteFoods: List<FoodDetail>) {
    }

    override fun showFamilyFoods(familyFoods: List<FoodDetail>) {
    }

    override fun showPartyFoods(partyFoods: List<FoodDetail>) {
    }

    override fun showError(exception: Exception) {
    }

    companion object {
        fun newInstance(): CollectionFragment = CollectionFragment()
    }
}

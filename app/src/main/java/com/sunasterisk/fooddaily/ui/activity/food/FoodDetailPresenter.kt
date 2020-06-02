package com.sunasterisk.fooddaily.ui.activity.food

import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.data.repository.RecipeRepository

class FoodDetailPresenter(
    private val recipeRepository: RecipeRepository
): FoodDetailContract.Presenter {

    override fun addToFavorite(food: FoodDetail) {
        recipeRepository.addToFavorite(food)
    }

    override fun addToFamily(food: FoodDetail) {
        recipeRepository.addToFamily(food)
    }

    override fun addToParty(food: FoodDetail) {
        recipeRepository.addToParty(food)
    }
}

package com.sunasterisk.fooddaily.ui.fragment.collection

import com.sunasterisk.fooddaily.data.model.FoodDetail

interface CollectionContract {

    interface View {
        fun showFavoriteFoods(favoriteFoods: List<FoodDetail>)
        fun showFamilyFoods(familyFoods: List<FoodDetail>)
        fun showPartyFoods(partyFoods: List<FoodDetail>)
        fun showError(exception: Exception)
    }

    interface Presenter {
        fun getAllFavoriteFoods()
        fun getAllFamilyFoods()
        fun getAllPartyFoods()
    }
}

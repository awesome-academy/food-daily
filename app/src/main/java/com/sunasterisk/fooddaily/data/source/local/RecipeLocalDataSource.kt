package com.sunasterisk.fooddaily.data.source.local

import com.sunasterisk.fooddaily.data.source.OnLoadedCallback
import com.sunasterisk.fooddaily.data.source.RecipeDataSource
import com.sunasterisk.fooddaily.data.source.local.base.LocalAsyncTask
import com.sunasterisk.fooddaily.data.source.local.base.LocalDataHandler
import com.sunasterisk.fooddaily.data.source.local.dao.FamilyFoodDAO
import com.sunasterisk.fooddaily.data.source.local.dao.FavoriteFoodDAO
import com.sunasterisk.fooddaily.data.source.local.dao.PartyFoodDAO
import com.sunasterisk.fooddaily.utils.Constants

class RecipeLocalDataSource private constructor(
    private val favoriteFoodDAO: FavoriteFoodDAO,
    private val partyFoodDAO: PartyFoodDAO,
    private val familyFoodDAO: FamilyFoodDAO
) : RecipeDataSource.Local {

    override fun getAllFavoriteFoods(callback: OnLoadedCallback<List<String>>) {
        LocalAsyncTask(object: LocalDataHandler<String, List<String>> {
            override fun execute(params: String): List<String> =
                favoriteFoodDAO.getAllFavoriteFoods()
        }, callback).execute(Constants.EMPTY_PARAMS)
    }

    override fun getAllPartyFoods(callback: OnLoadedCallback<List<String>>) {
        LocalAsyncTask(object: LocalDataHandler<String, List<String>> {
            override fun execute(params: String): List<String> =
                partyFoodDAO.getAllPartyFoods()
        }, callback).execute(Constants.EMPTY_PARAMS)
    }

    override fun getAllFamilyFoods(callback: OnLoadedCallback<List<String>>) {
        LocalAsyncTask(object: LocalDataHandler<String, List<String>> {
            override fun execute(params: String): List<String> =
                familyFoodDAO.getAllFamilyFoods()
        }, callback).execute(Constants.EMPTY_PARAMS)
    }

    companion object {
        private var instance: RecipeDataSource.Local? = null
        fun getInstance(
            favoriteFoodDAO: FavoriteFoodDAO,
            partyFoodDAO: PartyFoodDAO,
            familyFoodDAO: FamilyFoodDAO
        ): RecipeDataSource.Local =
            instance ?:
            RecipeLocalDataSource(
                favoriteFoodDAO,
                partyFoodDAO,
                familyFoodDAO
            ).also { instance = it }
    }
}

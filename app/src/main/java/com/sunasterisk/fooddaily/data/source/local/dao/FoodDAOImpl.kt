package com.sunasterisk.fooddaily.data.source.local.dao

import android.content.ContentValues
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.data.source.local.base.BaseDaoImpl
import com.sunasterisk.fooddaily.data.source.local.database.FoodDailyDatabase
import com.sunasterisk.fooddaily.utils.DatabaseKeys

private const val CHARACTER_EQUAL = "="

class FoodDAOImpl private constructor(
    foodDailyDatabase: FoodDailyDatabase
) : FoodDAO, BaseDaoImpl(foodDailyDatabase.writableDatabase) {

    private val database = foodDailyDatabase.writableDatabase

    override fun getAllFavoriteFoods(): List<FoodDetail> =
        getAllFoodsByCollection(FoodDetail.TYPE_FAVORITE)

    override fun getAllFamilyFoods(): List<FoodDetail> =
        getAllFoodsByCollection(FoodDetail.TYPE_FAMILY)

    override fun getAllPartyFoods(): List<FoodDetail> =
        getAllFoodsByCollection(FoodDetail.TYPE_PARTY)

    override fun insertFoodFavorite(foodDetail: FoodDetail) =
        resolveInsert(foodDetail, FoodDetail.TYPE_FAVORITE)

    override fun insertFoodFamily(foodDetail: FoodDetail) =
        resolveInsert(foodDetail, FoodDetail.TYPE_FAMILY)

    override fun insertFoodParty(foodDetail: FoodDetail) =
        resolveInsert(foodDetail, FoodDetail.TYPE_PARTY)

    override fun deleteFoodFavorite(foodDetail: FoodDetail) =
        deleteFood(foodDetail, FoodDetail.TYPE_FAVORITE)

    override fun deleteFoodFamily(foodDetail: FoodDetail) =
        deleteFood(foodDetail, FoodDetail.TYPE_FAMILY)

    override fun deleteFoodParty(foodDetail: FoodDetail) =
        deleteFood(foodDetail, FoodDetail.TYPE_PARTY)

    private fun getAllFoodsByCollection(collectionName: String): List<FoodDetail> {
        val foods = mutableListOf<FoodDetail>()
        val selection = collectionName + CHARACTER_EQUAL + DatabaseKeys.KEY_IN_COLLECTION
        val cursor = database.query(
            FoodDetail.TABLE_NAME,
            null,
            selection,
            null,
            null,
            null,
            null
        )
        cursor?.let {
            it.moveToFirst()
            while (!it.isAfterLast) {
                foods.add(FoodDetail(cursor))
                it.moveToNext()
            }
        }
        cursor.close()
        for (food in foods) {
            food.ingredients = getFoodIngredient(food.id.toString())
            food.instructions = getFoodInstruction(food.id.toString())
        }
        return foods
    }

    private fun resolveInsert(foodDetail: FoodDetail,collectionName: String) {
        if (checkFoodInDatabase(foodDetail)) {
            updateFood(foodDetail, collectionName)
        } else {
            insertFood(foodDetail, collectionName)
        }
    }

    private fun updateFood(foodDetail: FoodDetail, collectionName: String) {
        val whereClause = DatabaseKeys.KEY_WHERE_FOOD_ID + foodDetail.id
        val contentValues = ContentValues().apply {
            put(collectionName, DatabaseKeys.KEY_IN_COLLECTION)
        }
        database.update(FoodDetail.TABLE_NAME, contentValues, whereClause, null)
    }

    private fun deleteFood(foodDetail: FoodDetail, collectionName: String) {
        val whereClause = DatabaseKeys.KEY_WHERE_FOOD_ID + foodDetail.id
        val contentValues = ContentValues().apply {
            put(collectionName, DatabaseKeys.KEY_NOT_IN_COLLECTION)
        }
        database.update(FoodDetail.TABLE_NAME, contentValues, whereClause, null)
    }

    companion object {
        private var instance: FoodDAO? = null
        fun getInstance(foodDailyDatabase: FoodDailyDatabase): FoodDAO =
            instance ?: FoodDAOImpl(foodDailyDatabase).also { instance = it }
    }
}

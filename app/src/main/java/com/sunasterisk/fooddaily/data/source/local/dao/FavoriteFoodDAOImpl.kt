package com.sunasterisk.fooddaily.data.source.local.dao

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.data.source.local.database.FoodDailyDatabase
import com.sunasterisk.fooddaily.utils.DatabaseKeys

class FavoriteFoodDAOImpl private constructor(
    foodDailyDatabase: FoodDailyDatabase
) : FavoriteFoodDAO {

    private val database: SQLiteDatabase = foodDailyDatabase.writableDatabase

    override fun getAllFavoriteFoods(): List<String> {
        val foodIdList = mutableListOf<String>()
        val cursor = database.query(DatabaseKeys.TABLE_FAVORITE,
            null, null, null, null, null, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                // lay ra id food -> request api theo id
                val foodId = cursor.getColumnIndex(DatabaseKeys.FOOD_ID).toString()
                foodIdList.add(foodId)
            }
            cursor.close()
        }
        return foodIdList
    }

    override fun insertFoodFavorite(foodDetail: FoodDetail) {
        val contentValues = ContentValues().apply {
            put(DatabaseKeys.FOOD_ID, foodDetail.id)
        }
        database.insert(DatabaseKeys.TABLE_FAVORITE, null, contentValues)
    }

    override fun deleteFoodFavorite(foodDetail: FoodDetail) {
        database.delete(DatabaseKeys.TABLE_FAVORITE, foodDetail.id.toString(), null)
    }

    companion object {
        private var instance: FavoriteFoodDAO? = null
        fun getInstance(foodDailyDatabase: FoodDailyDatabase): FavoriteFoodDAO =
            instance ?: FavoriteFoodDAOImpl(foodDailyDatabase).also { instance = it }
    }
}
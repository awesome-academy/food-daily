package com.sunasterisk.fooddaily.data.source.local.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.data.source.local.database.FoodDailyDatabase
import com.sunasterisk.fooddaily.utils.DatabaseKeys

class FamilyFoodDAOImpl private constructor(
    foodDailyDatabase: FoodDailyDatabase
): FamilyFoodDAO {

    private var database: SQLiteDatabase = foodDailyDatabase.writableDatabase

    override fun getAllFamilyFoods(): List<String> {
        val foodIdList = mutableListOf<String>()
        val cursor = database.query(DatabaseKeys.TABLE_FAMILY,
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

    override fun insertFoodFamily(foodDetail: FoodDetail) {
        val contentValues = ContentValues().apply {
            put(DatabaseKeys.FOOD_ID, foodDetail.id)
        }
        database.insert(DatabaseKeys.TABLE_FAMILY, null, contentValues)
    }

    override fun deleteFoodFamily(foodDetail: FoodDetail) {
        database.delete(DatabaseKeys.TABLE_FAMILY, foodDetail.id.toString(), null)
    }

    companion object {
        private var instance: FamilyFoodDAO? = null
        fun getInstance(foodDailyDatabase: FoodDailyDatabase): FamilyFoodDAO =
            instance ?: FamilyFoodDAOImpl(foodDailyDatabase).also { instance = it }
    }
}

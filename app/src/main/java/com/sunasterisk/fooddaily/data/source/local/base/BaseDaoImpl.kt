package com.sunasterisk.fooddaily.data.source.local.base

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.data.model.Ingredient
import com.sunasterisk.fooddaily.data.model.Instruction
import com.sunasterisk.fooddaily.utils.DatabaseKeys
import com.sunasterisk.fooddaily.utils.getData

/**
 * For FoodDAO & CookingDAO (coming soon) use
 */
open class BaseDaoImpl(private val database: SQLiteDatabase) {

    private fun getAllFoodIds(): List<String> {
        val foodIds = mutableListOf<String>()
        val cursor = database.query(
            FoodDetail.TABLE_NAME,
            null, null, null, null, null, null
        )
        cursor?.let {
            it.moveToFirst()
            while (!it.isAfterLast) {
                foodIds.add(it.getData(FoodDetail.ID))
                cursor.moveToNext()
            }
        }
        cursor.close()
        return foodIds
    }

    fun getFoodIngredient(foodId: String): List<Ingredient>? {
        val ingredients = mutableListOf<Ingredient>()
        val selection = DatabaseKeys.KEY_WHERE_FOOD_ID + foodId
        val cursor = database.query(
            Ingredient.TABLE_NAME,
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
                val original = it.getString(it.getColumnIndex(Ingredient.ORIGINAL))
                ingredients.add(Ingredient(null, null, original))
                it.moveToNext()
            }
        }
        cursor.close()
        return ingredients
    }

    fun getFoodInstruction(foodId: String): List<Instruction>? {
        val instructions = mutableListOf<Instruction>()
        val selection = DatabaseKeys.KEY_WHERE_FOOD_ID + foodId
        val cursor = database.query(
            Instruction.TABLE_NAME,
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
                instructions.add(Instruction(it))
                cursor.moveToNext()
            }
        }
        cursor.close()
        return instructions
    }

    fun checkFoodInDatabase(foodDetail: FoodDetail): Boolean {
        var isFoodExist = false
        val foodIds = getAllFoodIds()
        for (foodId in foodIds) {
            if (foodId.compareTo(foodDetail.id.toString()) == 0) {
                isFoodExist = true
                break
            }
        }
        return isFoodExist
    }

    fun insertFood(foodDetail: FoodDetail, collectionName: String) {
        database.insert(
            FoodDetail.TABLE_NAME,
            null,
            foodDetail.getContentValues(collectionName)
        )
        insertToTableIngredient(foodDetail)
        insertToTableInstruction(foodDetail)
    }

    private fun insertToTableInstruction(foodDetail: FoodDetail) {
        foodDetail.instructions?.forEach { instruction ->
            database.insert(
                Instruction.TABLE_NAME,
                null,
                instruction.getContentValues(foodDetail)
            )
        }
    }

    private fun insertToTableIngredient(foodDetail: FoodDetail) {
        foodDetail.ingredients?.forEach { ingredient ->
            database.insert(
                Ingredient.TABLE_NAME,
                null,
                ingredient.getContentValues(foodDetail)
            )
        }
    }
}

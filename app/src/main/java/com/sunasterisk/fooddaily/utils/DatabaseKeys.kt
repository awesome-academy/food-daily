package com.sunasterisk.fooddaily.utils

import com.sunasterisk.fooddaily.data.model.FoodDetail

object DatabaseKeys {
    const val DATABASE_NAME = "food-daily-database-v.0.1"
    const val DATABASE_VERSION = 1

    const val KEY_IN_COLLECTION = "1"
    const val KEY_NOT_IN_COLLECTION = "1"
    const val KEY_WHERE_FOOD_ID = "${FoodDetail.ID}="
}

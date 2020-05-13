package com.sunasterisk.fooddaily.data.source.local.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sunasterisk.fooddaily.utils.DatabaseKeys

open class FoodDailyDatabase(
    context: Context
) : SQLiteOpenHelper(
    context,
    DatabaseKeys.DATABASE_NAME,
    null,
    DatabaseKeys.DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase?) {
        val favoriteQuery = "CREATE TABLE ${DatabaseKeys.TABLE_FAVORITE} " +
                "(" +
                "${DatabaseKeys.FAVORITE_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${DatabaseKeys.FOOD_ID} INTEGER" +
                ");"
        db?.execSQL(favoriteQuery)

        val partyQuery = "CREATE TABLE ${DatabaseKeys.TABLE_PARTY} " +
                "(" +
                "${DatabaseKeys.PARTY_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${DatabaseKeys.FOOD_ID} INTEGER" +
                ");"
        db?.execSQL(partyQuery)

        val familyQuery = "CREATE TABLE ${DatabaseKeys.TABLE_FAMILY} " +
                "(" +
                "${DatabaseKeys.FAMILY_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${DatabaseKeys.FOOD_ID} INTEGER" +
                ");"
        db?.execSQL(familyQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private var instance: FoodDailyDatabase? = null
        fun getInstance(context: Context): FoodDailyDatabase =
            instance ?: FoodDailyDatabase(context).also { instance = it }
    }
}

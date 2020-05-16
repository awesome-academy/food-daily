package com.sunasterisk.fooddaily.data.source.local.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sunasterisk.fooddaily.utils.DatabaseKeys

open class FoodDailyDatabase private constructor (
    context: Context
) : SQLiteOpenHelper(
    context,
    DatabaseKeys.DATABASE_NAME,
    null,
    DatabaseKeys.DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.run {
            execSQL(SQL_CREATE_TABLE_FAVORITE)
            execSQL(SQL_CREATE_TABLE_PARTY)
            execSQL(SQL_CREATE_TABLE_FAMILY)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.run {
            execSQL(SQL_DROP_TABLE_FAVORITE)
            execSQL(SQL_DROP_TABLE_PARTY)
            execSQL(SQL_DROP_TABLE_FAMILY)
        }
        onCreate(db)
    }

    companion object {
        private const val SQL_CREATE_TABLE_FAVORITE =
            "CREATE TABLE " + DatabaseKeys.TABLE_FAVORITE + " (" +
                    DatabaseKeys.FAVORITE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DatabaseKeys.FOOD_ID + " INTEGER);"

        private const val SQL_CREATE_TABLE_PARTY =
            "CREATE TABLE " + DatabaseKeys.TABLE_PARTY + " (" +
                    DatabaseKeys.PARTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DatabaseKeys.FOOD_ID + " INTEGER);"

        private const val SQL_CREATE_TABLE_FAMILY =
            "CREATE TABLE " + DatabaseKeys.TABLE_FAMILY + " (" +
                    DatabaseKeys.FAMILY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DatabaseKeys.FOOD_ID + " INTEGER);"

        private const val SQL_DROP_TABLE_FAVORITE =
            "DROP TABLE IF EXISTS " + DatabaseKeys.TABLE_FAVORITE

        private const val SQL_DROP_TABLE_PARTY =
            "DROP TABLE IF EXISTS " + DatabaseKeys.TABLE_PARTY

        private const val SQL_DROP_TABLE_FAMILY =
            "DROP TABLE IF EXISTS " + DatabaseKeys.TABLE_FAMILY

        private var instance: FoodDailyDatabase? = null
        fun getInstance(context: Context): FoodDailyDatabase =
            instance ?: FoodDailyDatabase(context).also { instance = it }
    }
}

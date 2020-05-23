package com.sunasterisk.fooddaily.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

private const val JSON_KEY_ID = "id"
private const val JSON_KEY_NAME = "name"
private const val JSON_KEY_ORIGINAL = "original"

@Parcelize
data class Ingredient(
    var id: String? = "",
    var name: String? = "",
    var original: String? = ""
) : Parcelable {
    constructor(jsonObject: JSONObject) : this (
        id = jsonObject.optString(JSON_KEY_ID),
        name = jsonObject.optString(JSON_KEY_NAME),
        original = jsonObject.optString(JSON_KEY_ORIGINAL)
    )
}

package com.sunasterisk.fooddaily.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

private const val JSON_KEY_STEP = "step"

@Parcelize
data class Instruction(var step: String? = "") : Parcelable {
    constructor(jsonObject: JSONObject) : this (step = jsonObject.optString(JSON_KEY_STEP))
}

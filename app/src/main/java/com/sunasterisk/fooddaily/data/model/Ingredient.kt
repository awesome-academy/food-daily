package com.sunasterisk.fooddaily.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ingredient(
    var id: String? = "",
    var name: String? = "",
    var original: String? = ""
) : Parcelable
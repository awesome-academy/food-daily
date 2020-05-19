package com.sunasterisk.fooddaily.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Instruction(
    var stepName: String? = ""
): Parcelable

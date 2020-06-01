package com.sunasterisk.fooddaily.data.model

import android.os.Parcelable
import com.sunasterisk.fooddaily.utils.getImageIngredientName
import com.sunasterisk.fooddaily.utils.getImageInstructionName
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

private const val JSON_KEY_NUMBER = "number"
private const val JSON_KEY_STEP = "step"
private const val TITLE_STEP = "Step "

@Parcelize
data class Instruction(
    var number: String? = "",
    var step: String? = "",
    var imageInstruction: String? = "",
    var imageIngredient: String? = ""
) : Parcelable {
    constructor(jsonObject: JSONObject) : this(
        number = jsonObject.optString(JSON_KEY_NUMBER),
        step = jsonObject.optString(JSON_KEY_STEP),
        imageInstruction = jsonObject.getImageInstructionName(),
        imageIngredient = jsonObject.getImageIngredientName()
    )

    fun getStepNumber(): String = TITLE_STEP + number
}

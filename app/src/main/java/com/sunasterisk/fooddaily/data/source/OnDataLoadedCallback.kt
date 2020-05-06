package com.sunasterisk.fooddaily.data.source

import com.sunasterisk.fooddaily.data.model.FoodDetails
import java.lang.Exception

interface OnDataLoadedCallback {
    fun onSuccess(data: List<FoodDetails>)
    fun onFailure(exception: Exception)
}

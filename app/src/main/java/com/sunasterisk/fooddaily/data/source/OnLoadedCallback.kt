package com.sunasterisk.fooddaily.data.source

import com.sunasterisk.fooddaily.data.model.FoodDetail
import java.lang.Exception

interface OnLoadedCallback<T> {
    fun onSuccess(data: List<T>)
    fun onFailure(exception: Exception)
}

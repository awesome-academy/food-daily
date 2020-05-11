package com.sunasterisk.fooddaily.data.source

import java.lang.Exception

interface OnLoadedCallback<T> {
    fun onSuccess(data: List<T>)
    fun onFailure(exception: Exception)
}

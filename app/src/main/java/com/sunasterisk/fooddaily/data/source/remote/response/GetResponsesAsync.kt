package com.sunasterisk.fooddaily.data.source.remote.response

import android.os.AsyncTask
import com.sunasterisk.fooddaily.data.model.FoodDetails
import com.sunasterisk.fooddaily.data.source.OnDataLoadedCallback

class GetResponsesAsync(
    private val dataResponseHandler: DataResponseHandler,
    private val callback: OnDataLoadedCallback
): AsyncTask<String, Void, List<FoodDetails>>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: String): List<FoodDetails> {
        // xu ly request den api lay data ve parse sang food model
        return dataResponseHandler.getResponse(params[0])
    }

    override fun onPostExecute(result: List<FoodDetails>?) {
        super.onPostExecute(result)
        if (result != null) {
            callback.onSuccess(result)
        } else {
            exception?.let { callback.onFailure(it) }
        }
    }
}

package com.sunasterisk.fooddaily.data.source.remote.response

import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.utils.Constants
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class SearchResponseHandler private constructor(): DataResponseHandler {
    override fun getResponse(urlRequest: String): List<FoodDetail> {
        val strJSON = handleConnect(URL(urlRequest))
        return convertJSONToFood(strJSON)
    }

    private fun handleConnect(url: URL): String {
        var result = ""
        var urlConnection: HttpURLConnection? = null
        try {
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.apply {
                doInput = true
                requestMethod = Constants.METHOD_GET
                connect()
            }
            val responseCode = urlConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val scanner = Scanner(url.openStream())
                while (scanner.hasNext()) {
                    result += scanner.nextLine()
                }
                scanner.close()
            } else {
                throw IOException()
            }
        } finally {
            urlConnection?.disconnect()
        }
        return result
    }

    companion object {
        private var instance: SearchResponseHandler? = null
        fun getInstance(): SearchResponseHandler =
            instance ?: SearchResponseHandler().also { instance = it }
    }
}

package com.example.labo4

import android.net.Uri
import android.view.View
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*


class NetworkUtility() {

    val BASE_URL: String = "http://omdbapi.com/"

    val URL_TOKEN: String = "a81a002e"

    fun buildUrl(movieName: String): URL {
        val builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter("apikey", URL_TOKEN)
                .appendQueryParameter("t", movieName)
                .build()


        lateinit var url: URL
        try {
            url = URL(builtUri.toString())
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }

        return url
    }

    @Throws(IOException::class)
    fun getResponseFromHttpUrl(url: URL): String? {
        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            val `in` = urlConnection.getInputStream()
            //val `fot` = urlConnection.getInputStream()

            val scanner = Scanner(`in`)
            scanner.useDelimiter("\\A")

            val hasInput = scanner.hasNext()
            return if (hasInput) {
                 scanner.next()
            } else {
                 null
            }
        } finally {
            urlConnection.disconnect()
        }
    }


}
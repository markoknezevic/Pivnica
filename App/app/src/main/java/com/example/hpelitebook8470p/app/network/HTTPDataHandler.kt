package com.example.hpelitebook8470p.app.network

import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class HTTPDataHandler(var urlString: String) {
    val httpData: String?
        get() {

            try {
                val url = URL(urlString)
                var urlConnection = url.openConnection() as HttpURLConnection

                if (urlConnection.responseCode == 200) {

                    val inputStream = BufferedInputStream(urlConnection.inputStream)
                    var reader = BufferedReader(InputStreamReader(inputStream))

                    val builder = StringBuilder()

                    var line = reader.readLine()
                    while (line != null) {
                        builder.append(line)
                        line = reader.readLine()
                    }
                    val string = builder.toString()

                    urlConnection.disconnect()
                    return string
                }
            } catch (e: MalformedURLException) {
                println(e)
            } catch (e: IOException) {
                println(e)
            }
            return null
        }
}
package com.example.hpelitebook8470p.app.network.parsers

import com.example.hpelitebook8470p.app.model.Address
import com.example.hpelitebook8470p.app.model.Brewery
import org.json.JSONException
import org.json.JSONObject

class BreweriesParser {
    lateinit var breweries: ArrayList<Brewery>
    fun parse(json: String) {
        breweries = ArrayList<Brewery>()
        try {
            val jsonObject = JSONObject(json)
            val jsonArray = jsonObject.getJSONArray("breweries")

            for (i in 0 until jsonArray.length()) {
                val jsonBrewery = jsonArray[i] as JSONObject
                val id = jsonBrewery.getLong("id")
                val name = jsonBrewery.getString("name")
                val imageURL = jsonBrewery.getString("imageURL")

                val addressJSONObject = jsonBrewery.getJSONObject("address")
                val street = addressJSONObject.getString("street")
                val number = addressJSONObject.getString("number")
                val zipcode = addressJSONObject.getString("zipcode")
                val city = addressJSONObject.getString("city")

                val address = Address(street, number, zipcode, city)
                val brewery = Brewery(id, name, address, imageURL)
                breweries.add(brewery)

            }
        } catch (e: JSONException) {
            println(e)
        }
    }
}
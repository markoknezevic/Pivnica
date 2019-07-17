package com.example.hpelitebook8470p.app.network.parsers

import com.example.hpelitebook8470p.app.model.*
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat

class DetailsParser {
    lateinit var brewery: Brewery
    fun parse(json: String) {
        try {
            val jsonBrewery = JSONObject(json)
            val id = jsonBrewery.getLong("id")
            val name = jsonBrewery.getString("name")

            val image = jsonBrewery.getString("image")
            val description = jsonBrewery.getString("description")

            val addressJSONObject = jsonBrewery.getJSONObject("address")
            val street = addressJSONObject.getString("street")
            val number = addressJSONObject.getString("number")
            val zipcode = addressJSONObject.getString("zipcode")
            val city = addressJSONObject.getString("city")

            val address = Address(street, number, zipcode, city)

            val openingTimesArray = jsonBrewery.getJSONArray("restaurantOpeningTimes")
            val arrayOT = ArrayList<OpeningTime>()
            for (i in 0 until openingTimesArray.length()) {
                val time = openingTimesArray[i] as JSONObject
                val day = time.getInt("day")
                val open = time.getString("open")
                val close = time.getString("close")
                val openingTime = OpeningTime(day, open, close)
                arrayOT.add(openingTime)
            }
            val positionJSONObject = jsonBrewery.getJSONObject("latLong")
            val latitude = positionJSONObject.getDouble("latitude")
            val longitude = positionJSONObject.getDouble("longitude")
            val position = Position(latitude, longitude)

            val servicesArray = jsonBrewery.getJSONArray("services")
            val service = ArrayList<Service>()
            for (i in 0 until servicesArray.length()) {
                val srvc = servicesArray[i] as JSONObject
                val name = srvc.getString("name")
                val s = Service(name)
                service.add(s)
            }



            val reviewsArray = jsonBrewery.getJSONArray("reviews")
            val arrayR = ArrayList<Review>()
            for (i in 0 until reviewsArray.length()) {

                val rvw = reviewsArray[i] as JSONObject
                val rating = rvw.getInt("rating")
                val description = rvw.getString("description")
                val date = rvw.getString("date")
                val d = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(date)
                val reviewName = rvw.getString("reviewName")

                val cst = rvw.getJSONObject("customer")
                val id = cst.getLong("id")
                val lastName = cst.getString("lastName")
                val firstName = cst.getString("firstName")
                val customer = Customer(id, lastName, firstName)

                val review = Review(rating, description, d, reviewName, customer)
                arrayR.add(review)
            }



            brewery = Brewery(id, name, address, image, description, arrayOT, position, service, arrayR)


        } catch (e: JSONException) {
            print(e)
        }
    }
}
package com.example.hpelitebook8470p.app

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.hpelitebook8470p.app.model.Brewery
import com.example.hpelitebook8470p.app.network.HTTPDataHandler
import com.example.hpelitebook8470p.app.network.parsers.DetailsParser
import com.example.hpelitebook8470p.app.view.ServicesAdapter

class BreweryDetailsActivity : AppCompatActivity() {

    lateinit var image: ImageView
    lateinit var name: TextView
    lateinit var description: TextView
    lateinit var info: TextView
    lateinit var ot: TextView
    lateinit var monday: TextView
    lateinit var tuesday: TextView
    lateinit var wednesday: TextView
    lateinit var thursday: TextView
    lateinit var friday: TextView
    lateinit var saturday: TextView
    lateinit var sunday: TextView
    lateinit var monOT: TextView
    lateinit var tueOT: TextView
    lateinit var wedOT: TextView
    lateinit var thuOT: TextView
    lateinit var friOT: TextView
    lateinit var satOT: TextView
    lateinit var sunOT: TextView
    lateinit var services: RecyclerView

    lateinit var getthere: TextView
    lateinit var street: TextView
    lateinit var comment: TextView
    lateinit var reviews: TextView
    lateinit var rating: TextView
    lateinit var numreview: TextView
    lateinit var customer: TextView
    lateinit var rating2: TextView
    lateinit var re: TextView
    lateinit var img1: ImageView
    lateinit var img2: ImageView

    lateinit var detailsView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brewery_details)
        initSth()
        findDetails()
    }


    private fun initSth() {

        image = findViewById<ImageView>(R.id.image_detail)
        name = findViewById<TextView>(R.id.name_detail)
        description = findViewById<TextView>(R.id.description_detail)
        info = findViewById<TextView>(R.id.info_detail)
        ot = findViewById<TextView>(R.id.openingtime_detail)
        monday = findViewById<TextView>(R.id.monday)
        tuesday = findViewById<TextView>(R.id.tuesday)
        wednesday = findViewById<TextView>(R.id.wednesday)
        thursday = findViewById<TextView>(R.id.thursday)
        friday = findViewById<TextView>(R.id.friday)
        saturday = findViewById<TextView>(R.id.saturday)
        sunday = findViewById<TextView>(R.id.sunday)
        monOT = findViewById<TextView>(R.id.mondayOpeningTime)
        tueOT = findViewById<TextView>(R.id.tuesdayOpeningTime)
        wedOT = findViewById<TextView>(R.id.wednesdayOpeningTime)
        thuOT = findViewById<TextView>(R.id.thursdayOpeningTime)
        friOT = findViewById<TextView>(R.id.fridayOpeningTime)
        satOT = findViewById<TextView>(R.id.saturdayOpeningTime)
        sunOT = findViewById<TextView>(R.id.sundayOpeningTime)
        //services = findViewById<RecyclerView>(R.id.recyclerViewServices)
        detailsView = findViewById<RecyclerView>(R.id.recyclerViewServices)

        getthere = findViewById<TextView>(R.id.getthere_detail)
        street = findViewById<TextView>(R.id.street_detail)
        comment = findViewById<TextView>(R.id.comment_detail)
        reviews = findViewById<TextView>(R.id.reviews_detail)
        rating = findViewById<TextView>(R.id.rating_detail)
        numreview = findViewById<TextView>(R.id.numreview_detail)
        customer = findViewById<TextView>(R.id.customer_detail)
        rating2 = findViewById<TextView>(R.id.rating_detail2)
        re = findViewById<TextView>(R.id.re_detail)
        img1 = findViewById<ImageView>(R.id.img1)
        img2 = findViewById<ImageView>(R.id.img2)

    }

    private fun findDetails() {
        val detailsAsyncTask = DetailsAsyncTask()
        detailsAsyncTask.execute("https://jsonblob.com/api/jsonblob/092dc7b6-dc36-11e8-8af2-23a086d46be1")

    }

     private fun showServices(brewery: Brewery) {

         name.text = brewery.name
         description.text = brewery.description
         Glide.with(this).load(brewery.imageURL).into(image)
         detailsView.layoutManager = LinearLayoutManager(this)

         val adapter = ServicesAdapter(brewery.services!!.toList(), this)
         detailsView.adapter = adapter


     }


    inner class DetailsAsyncTask : AsyncTask<String, Void, String?>() {
        override fun doInBackground(vararg strings: String?): String? {
            val urlString = strings[0]
            urlString?.let {
                return HTTPDataHandler(it).httpData

            }
            return null
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            result?.let {
                val parser = DetailsParser()
                parser.parse(it)
                showServices(parser.brewery)
            }
        }
    }
}

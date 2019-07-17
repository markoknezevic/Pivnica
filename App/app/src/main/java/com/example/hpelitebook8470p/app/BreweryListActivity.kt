package com.example.hpelitebook8470p.app

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.hpelitebook8470p.app.model.Brewery
import com.example.hpelitebook8470p.app.network.HTTPDataHandler
import com.example.hpelitebook8470p.app.network.parsers.BreweriesParser
import com.example.hpelitebook8470p.app.view.ListAdapter

class BreweryListActivity : AppCompatActivity() {
    lateinit var listingView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.brewery_list_activity)
        initListView()
        findAllBreweries()
    }

    private fun initListView() {

        listingView = findViewById<RecyclerView>(R.id.recyclerView)
        listingView.layoutManager = LinearLayoutManager(this)

    }

    private fun showBreweries(breweries: Collection<Brewery>) {
        val adapter = ListAdapter(breweries, this)
        listingView.adapter = adapter
    }

    private fun findAllBreweries() {
        val breweriesAsyncTask = BreweriesAsyncTask()
        breweriesAsyncTask.execute("https://jsonblob.com/api/jsonblob/1f89c564-dc35-11e8-8af2-7d283b3abd4b")
    }

    inner class BreweriesAsyncTask : AsyncTask<String, Void, String?>() {
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
                val parser = BreweriesParser()
                parser.parse(it)
                showBreweries(parser.breweries)
            }
        }
    }
}

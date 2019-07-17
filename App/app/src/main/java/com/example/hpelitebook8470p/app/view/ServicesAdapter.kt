package com.example.hpelitebook8470p.app.view

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hpelitebook8470p.app.R
import com.example.hpelitebook8470p.app.model.Service


class ServicesAdapter(
    val services: List<Service>,
    val context: Context
) : RecyclerView.Adapter<ListingService>() {

    override fun getItemCount(): Int {
        return services.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingService {
        val view = LayoutInflater.from(context).inflate(R.layout.services, parent, false)
        return ListingService(view)
    }

    override fun onBindViewHolder(listingService: ListingService, position: Int) {
        val service = services.elementAt(position)
        listingService.setData(service)
    }
}
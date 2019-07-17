package com.example.hpelitebook8470p.app.view

import android.support.v7.widget.RecyclerView
import android.view.View

import android.widget.TextView
import com.example.hpelitebook8470p.app.R
import com.example.hpelitebook8470p.app.model.Service

class ListingService : RecyclerView.ViewHolder {
    private val serviceText: TextView


    constructor(view: View) : super(view) {
        serviceText = view.findViewById(R.id.name)

    }

    fun setData(service: Service) {
        serviceText.text = service.name

    }
}
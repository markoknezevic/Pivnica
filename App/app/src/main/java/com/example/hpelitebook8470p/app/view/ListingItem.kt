package com.example.hpelitebook8470p.app.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.hpelitebook8470p.app.R
import com.example.hpelitebook8470p.app.model.Brewery


class ListingItem : RecyclerView.ViewHolder {
    private val imageView: ImageView
    private val name: TextView
    private val address: TextView
    val breweryCard: RelativeLayout

    constructor(view: View) : super(view) {
        imageView = view.findViewById(R.id.imageView)
        name = view.findViewById(R.id.name)
        address = view.findViewById(R.id.address)
        breweryCard = view.findViewById(R.id.breweryCard)
    }

    fun setData(brewery: Brewery) {
        name.text = brewery.name
        address.text = brewery.address.street + " " +
                brewery.address.number + ", " +
                brewery.address.zipCode + " " +
                brewery.address.city
        Glide.with(itemView.context).load(brewery.imageURL).into(imageView)
    }
}
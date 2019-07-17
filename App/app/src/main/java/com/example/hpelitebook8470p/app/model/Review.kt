package com.example.hpelitebook8470p.app.model

import java.util.*

class Review(
    val rating: Int,
    val description: String,
    val date: Date,
    val reviewName: String,
    val customer: Customer
)
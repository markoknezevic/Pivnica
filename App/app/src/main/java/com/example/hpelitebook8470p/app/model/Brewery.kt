package com.example.hpelitebook8470p.app.model

import java.util.*

class Brewery(
    val id: Long,
    val name: String,
    val address: Address,
    val imageURL: String,
    val description: String? = null,
    val openingTimes: Collection<OpeningTime>? = null,
    val position: Position? = null,
    val services: Collection<Service>? = null,
    val review: Collection<Review>? = null
)

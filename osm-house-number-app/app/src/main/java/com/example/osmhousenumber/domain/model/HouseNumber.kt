package com.example.osmhousenumber.domain.model

data class HouseNumber(
    val number: String,
    val street: String,
    val lat: Double,
    val lon: Double
)

package com.example.coffeeshop.domain.models

@kotlinx.serialization.Serializable
data class PopularModel(
    val title: String = "",
    val description: String = "",
    val picUrl: List<String> = emptyList(),
    val price: Double = 0.0,
    val rating: Double = 0.0,
    var numberInCart: Int = 0,
    val categoryId: String = "",
    var extra: String = ""
) : java.io.Serializable

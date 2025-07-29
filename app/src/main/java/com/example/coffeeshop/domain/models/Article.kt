package com.example.coffeeshop.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val title: String,
    val description:String? = null,
    val url:String,
    val urlToImage:String? = null
    )

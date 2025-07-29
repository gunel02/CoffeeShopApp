package com.example.coffeeshop.domain.models

import kotlinx.serialization.Serializable

@Serializable

data class BannerModel(
    val id: String,
    val url: String
)

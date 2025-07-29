package com.example.coffeeshop.domain.models

import kotlinx.serialization.Serializable
@Serializable

data class CategoryModel(
    val id: String,
    val title: String
)

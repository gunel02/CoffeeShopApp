package com.example.coffeeshop.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coffeeshop.data.network.KtorClient
import com.example.coffeeshop.domain.models.BannerModel
import com.example.coffeeshop.domain.models.CategoryModel
import io.ktor.client.call.body
import io.ktor.client.request.get

class MainRepository {

    suspend fun loadBanners(): List<BannerModel> {
        return KtorClient.client
            .get("https://688801a4adf0e59551b8c827.mockapi.io/api/v1/banners")
            .body()
    }


    suspend fun loadCategory(): List<CategoryModel> {
        return KtorClient.client
            .get("https://688801a4adf0e59551b8c827.mockapi.io/api/v1/categories")
            .body()
    }


}

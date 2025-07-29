package com.example.coffeeshop.data.network.service

import com.example.coffeeshop.data.network.KtorClient
import com.example.coffeeshop.data.network.NetworkRoutes
import com.example.coffeeshop.domain.models.NewsResponse
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType

class NewsService {

    suspend fun getTopHeadlines(
//        country: String = "us",
//        query: String = "coffee"
    ): NewsResponse {
        return KtorClient.client.get("https://newsapi.org/v2/everything") {
//            parameter("country", country)
//            parameter("q", query)
            parameter("q", "\"coffee beans\" OR \"coffee shop\" OR \"coffee drink\"")
            parameter("domains", "sprudge.com,perfectdailygrind.com,baristamagazine.com")
//            parameter("pageSize", 10) // limit to 10 articles
            parameter("sortBy", "publishedAt")
            parameter("apiKey", NetworkRoutes.API_KEY)
            accept(ContentType.Application.Json)
        }.body()
    }
}

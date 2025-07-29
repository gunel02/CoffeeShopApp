package com.example.coffeeshop.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.data.network.service.NewsService
import com.example.coffeeshop.domain.models.Article
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val newsService = NewsService()

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error


    fun fetchNews() {
        _isLoading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                val response = newsService.getTopHeadlines()
                Log.d("tagg", "Fetched ${response.articles.size} coffee articles")
                _articles.value = response.articles
            } catch (e: Exception) {
                Log.e("tagg", "Error: ${e.message}")
                _error.value = e.localizedMessage ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

}

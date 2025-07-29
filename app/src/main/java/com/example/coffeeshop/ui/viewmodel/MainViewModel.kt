package com.example.coffeeshop.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.domain.models.BannerModel
import com.example.coffeeshop.domain.models.CategoryModel
import com.example.coffeeshop.domain.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = MainRepository()

    private val _banners = MutableLiveData<List<BannerModel>>()
    val banners: LiveData<List<BannerModel>> = _banners


    private val _categories = MutableLiveData<List<CategoryModel>>()
    val categories: LiveData<List<CategoryModel>> = _categories

    fun fetchBanners() {
        viewModelScope.launch {
            try {
                val result = repository.loadBanners()
                _banners.value = result
            } catch (e: Exception) {
                Log.e("tagg", "Error fetching banners: ${e.message}")
            }
        }
    }


    fun fetchCategories() {
        viewModelScope.launch {
            try {
                val result = repository.loadCategory()
                _categories.value = result
            } catch (e: Exception) {
                Log.e("tagg", "Error fetching banners: ${e.message}")
            }
        }
    }





}


package com.example.coffeeshop.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.domain.models.BannerModel
import com.example.coffeeshop.domain.models.CategoryModel
import com.example.coffeeshop.domain.models.PopularModel
import com.example.coffeeshop.domain.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = MainRepository()

    private val _banners = MutableLiveData<List<BannerModel>>()
    val banners: LiveData<List<BannerModel>> = _banners


    private val _categories = MutableLiveData<List<CategoryModel>>()
    val categories: LiveData<List<CategoryModel>> = _categories


    private val _populars = MutableLiveData<List<PopularModel>>()
    val populars: LiveData<List<PopularModel>> = _populars


    private val _itemsList = MutableLiveData<List<PopularModel>>()
    val itemsList: LiveData<List<PopularModel>> = _itemsList

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

    fun fetchPopular() {
        viewModelScope.launch {
            try {
                val result = repository.loadPopular()
                _populars.value = result
            } catch (e: Exception) {
                Log.e("tagg", "Error fetching banners: ${e.message}")
            }
        }
    }

//    fun fetchItemsList(categoryId: String) {
//        viewModelScope.launch {
//            try {
//                val result = repository.loadItemCategory(categoryId)
//                _itemsList.value = result
//            } catch (e: Exception) {
//            }
//        }
//    }

    fun fetchItemsList(categoryId: String) {
        viewModelScope.launch {
            try {
                val result = repository.loadItemCategory(categoryId)
                    .filter { it.categoryId == categoryId } // ✅ Very important
                Log.d("TAGGGGG", "Filtered items for $categoryId: ${result.size}") // 👈 add this

                _itemsList.value = result
            } catch (e: Exception) {
                Log.e("tagg", "Error fetching items: ${e.message}")
            }
        }
    }

}


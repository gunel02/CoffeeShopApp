package com.example.coffeeshop.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.coffeeshop.databinding.ActivityMainBinding
import com.example.coffeeshop.ui.adapter.ArticleAdapter
import com.example.coffeeshop.ui.adapter.CategoryAdapter
import com.example.coffeeshop.ui.adapter.PopularAdapter
import com.example.coffeeshop.ui.viewmodel.MainViewModel
import com.example.coffeeshop.ui.viewmodel.NewsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()
    private val newsViewModel: NewsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initBanner()
        initNews()
        initCategory()
        initPopular()
        initBottomMenu()


        viewModel.fetchBanners()
        viewModel.fetchCategories()
        newsViewModel.fetchNews()
        viewModel.fetchPopular()

    }

    private fun initBottomMenu() {
        binding.cartBtn.setOnClickListener{
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.banners.observe(this) { bannerList ->
            if (!bannerList.isNullOrEmpty()) {
                Log.d("taggg", "Loaded banner URL: ${bannerList[0].url}")
                Glide.with(this)
                    .load(bannerList[0].url)
                    .into(binding.banner)
            }
            binding.progressBarBanner.visibility = View.GONE
        }
    }

    private fun initCategory() {
        binding.progressBarBanner.visibility = View.VISIBLE
        binding.progressBarCategory.visibility = View.VISIBLE

        viewModel.categories.observe(this) { categoryList ->
            if (!categoryList.isNullOrEmpty()) {
                val adapter = CategoryAdapter(categoryList)
                binding.categoryRecyclerView.adapter = adapter
                binding.categoryRecyclerView.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            }
            binding.progressBarCategory.visibility = View.GONE
        }
    }


    private fun initNews() {
        binding.progressBarNews.visibility = View.VISIBLE

        newsViewModel.articles.observe(this) { articles ->
            Log.d("tagg", "Articles count: ${articles?.size}")

            if (!articles.isNullOrEmpty()) {
                val adapter = ArticleAdapter(articles)
                binding.newsRecyclerView.adapter = adapter
                binding.newsRecyclerView.layoutManager = LinearLayoutManager(this)
            }

            binding.progressBarNews.visibility = View.GONE
        }

        // Optional: observe error state and hide loading
        newsViewModel.error.observe(this) { error ->
            if (error != null) {
                Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
                binding.progressBarNews.visibility = View.GONE
            }
        }
    }


    private fun initPopular() {
        binding.progressBarPopular.visibility = View.VISIBLE

        viewModel.populars.observe(this) { popularList ->
            Log.d("POPULAR_TAG", "Popular list: $popularList")

            if (!popularList.isNullOrEmpty()) {
                val adapter = PopularAdapter(popularList)
                binding.recyclerViewPopular.adapter = adapter
                binding.recyclerViewPopular.layoutManager = GridLayoutManager(this, 2)
            } else {
                Log.e("POPULAR_TAG", "Popular list is empty or null")
            }

            binding.progressBarPopular.visibility = View.GONE
        }
    }

}
package com.example.coffeeshop.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.ActivityItemsListBinding
import com.example.coffeeshop.databinding.ActivityMainBinding
import com.example.coffeeshop.ui.adapter.ItemListCategoryAdapter
import com.example.coffeeshop.ui.viewmodel.MainViewModel
import com.example.coffeeshop.ui.viewmodel.NewsViewModel

class ItemsListActivity : AppCompatActivity() {


    private lateinit var binding: ActivityItemsListBinding
    private val viewModel = MainViewModel()
    private var id: String = ""
    private var title: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundles()

        initList()
        viewModel.fetchItemsList(id)
    }

    private fun initList() {
            binding.apply {
                progressBar.visibility = View.VISIBLE
//                viewModel.itemsList.observe(this@ItemsListActivity, Observer{
//                    listView.layoutManager = GridLayoutManager(this@ItemsListActivity,2)
//                    listView.adapter = ItemListCategoryAdapter(it)
//                    progressBar.visibility = View.GONE
//                })
                viewModel.itemsList.observe(this@ItemsListActivity) { items ->
                    Log.d("TAGGGGGGGGGG", "Fetched items size: ${items.size}")
                    listView.layoutManager = GridLayoutManager(this@ItemsListActivity, 2)
                    listView.adapter = ItemListCategoryAdapter(items)
                    progressBar.visibility = View.GONE
                }

                backBtn.setOnClickListener{finish()}
            }
    }

    private fun getBundles() {
        id = intent.getStringExtra("id")!!
        title = intent.getStringExtra("title")!!
        Log.d("TAGGGGG", "ID from intent: $id") // 👈 check this in log

        binding.categoryTxt.text = title
    }
}
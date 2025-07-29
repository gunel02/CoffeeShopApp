//package com.example.coffeeshop.ui.adapter
//
//import android.content.Context
//import android.os.Looper
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.coffeeshop.R
//import com.example.coffeeshop.databinding.ItemCategoryRvBinding
//import com.example.coffeeshop.databinding.ItemPopularRvBinding
//import com.example.coffeeshop.domain.models.CategoryModel
//
//class PopularAdapter(private val popularModel: List<PopularModel>) :
//    RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {
//
//    private lateinit var context: Context
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
//        context = parent.context
//        val binding =
//            ItemPopularRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return PopularViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
//
//        val popular = popularModel[position]
//
//    }
//
//
//
//    override fun getItemCount(): Int = popularModel.size
//
//    inner class PopularViewHolder(val binding: ItemPopularRvBinding) :
//        RecyclerView.ViewHolder(binding.root)
//}
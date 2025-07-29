package com.example.coffeeshop.ui.adapter

import android.content.Context
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.ItemCategoryRvBinding
import com.example.coffeeshop.domain.models.CategoryModel

class CategoryAdapter(private val categoryModel: List<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private lateinit var context: Context
    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        context = parent.context
        val binding =
            ItemCategoryRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val category = categoryModel[position]
        holder.binding.titleCat.text = category.title

        holder.binding.root.setOnClickListener{
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        android.os.Handler(Looper.getMainLooper()).postDelayed({

        },500)

        if(selectedPosition == position){
            holder.binding.root.setBackgroundResource(R.drawable.background_category_dark_brown)
            holder.binding.titleCat.setTextColor(context.getColor(com.example.coffeeshop.R.color.white))
        }else{
            holder.binding.root.setBackgroundResource(R.drawable.background_category_white)
            holder.binding.titleCat.setTextColor(context.getColor(com.example.coffeeshop.R.color.dark_brown))

        }
    }



    override fun getItemCount(): Int = categoryModel.size

    inner class CategoryViewHolder(val binding: ItemCategoryRvBinding) :
        RecyclerView.ViewHolder(binding.root)
}
package com.example.coffeeshop.ui.adapter

import android.content.Context
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.ItemCategoryRvBinding
import com.example.coffeeshop.databinding.ItemPopularListRvBinding
import com.example.coffeeshop.databinding.ItemPopularRvBinding
import com.example.coffeeshop.domain.models.PopularModel

class ItemListCategoryAdapter(private val popularModel: List<PopularModel>) :
    RecyclerView.Adapter<ItemListCategoryAdapter.ItemListCategoryViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListCategoryViewHolder {
        context = parent.context
        val binding =
            ItemPopularListRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemListCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemListCategoryViewHolder, position: Int) {

        val popular = popularModel[position]

        holder.binding.titleTxt.text = popular.title
        holder.binding.priceTxt.text = "$" + popular.price.toString()
        holder.binding.subtitleTxt.text = popular.extra.toString()

        Glide.with(context)
            .load(popular.picUrl[0])
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener{

        }


    }



    override fun getItemCount(): Int = popularModel.size

    inner class ItemListCategoryViewHolder(val binding: ItemPopularListRvBinding) :
        RecyclerView.ViewHolder(binding.root)
}
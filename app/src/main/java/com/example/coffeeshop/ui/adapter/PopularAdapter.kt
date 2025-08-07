package com.example.coffeeshop.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeeshop.databinding.ItemPopularRvBinding
import com.example.coffeeshop.domain.models.PopularModel
import com.example.coffeeshop.ui.activity.DetailActivity

class PopularAdapter(private val popularModel: List<PopularModel>) :
    RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        context = parent.context
        val binding =
            ItemPopularRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {

        val popular = popularModel[position]

        holder.binding.titleTxt.text = popular.title
        holder.binding.priceTxt.text = "$" + popular.price.toString()
        holder.binding.subtitleTxt.text = popular.extra

        Glide.with(context)
            .load(popular.picUrl[0])
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener{

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object",popular)
            context.startActivity(intent)
        }

    }


    override fun getItemCount(): Int = popularModel.size

    inner class PopularViewHolder(val binding: ItemPopularRvBinding) :
        RecyclerView.ViewHolder(binding.root)
}
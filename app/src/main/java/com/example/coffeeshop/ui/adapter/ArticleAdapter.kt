package com.example.coffeeshop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeeshop.databinding.ItemArticleRvBinding
import com.example.coffeeshop.domain.models.Article

class ArticleAdapter(private val articles: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: ItemArticleRvBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]

        holder.binding.title.text = article.title
        holder.binding.description.text = article.description

        Glide.with(holder.binding.root.context)
            .load(article.urlToImage)
            .into(holder.binding.image)
    }

    override fun getItemCount(): Int = articles.size
}

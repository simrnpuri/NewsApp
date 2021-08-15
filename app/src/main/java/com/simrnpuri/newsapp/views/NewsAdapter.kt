package com.simrnpuri.newsapp.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.simrnpuri.newsapp.R
import com.simrnpuri.newsapp.data.database.model.LocalArticle
import com.simrnpuri.newsapp.data.domain.Article

class NewsAdapter( val listener: OnArticleClickListener) : RecyclerView.Adapter<NewsAdapter.ArtileViewHolder>(){

    private var articles:List<LocalArticle> = ArrayList()

    fun submitList(list: List<LocalArticle>){
        this.articles = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ArtileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtileViewHolder, position: Int) {
        val article = articles[position]
        holder.newsTitle.text = article.title
        holder.newsDescription.text = article.description
        holder.faveIcon.isSelected = article.isFav == true
        Glide.with(holder.newsImage.context).load(article.urlToImage).into(holder.newsImage)
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
            holder.itemView.context.startActivity(intent)
        }

        holder.faveIcon.setOnClickListener{
            listener.onFavCLick(article)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ArtileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var newsImage = itemView.findViewById<ImageView>(R.id.newsIV)
        var newsTitle = itemView.findViewById<TextView>(R.id.titleTV)
        var newsDescription = itemView.findViewById<TextView>(R.id.desccriptionTV)
        var faveIcon = itemView.findViewById<ImageView>(R.id.fave_icon)
    }

    interface OnArticleClickListener{
        fun onFavCLick(article:LocalArticle)
        fun onArticleClick(url:String)
    }

}
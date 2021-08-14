package com.simrnpuri.newsapp.data.domain

import com.simrnpuri.newsapp.data.database.model.LocalArticle

data class Article (val author:String, val title:String, val description:String, val url:String, val urlToImage:String){
    fun toLocal():LocalArticle{
        return LocalArticle(null,author,title,description,url, urlToImage, false)
    }
}
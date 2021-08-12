package com.simrnpuri.newsapp.data

import com.simrnpuri.newsapp.Article

data class News(val totalResults:Int,val articles : List<Article>) {
}
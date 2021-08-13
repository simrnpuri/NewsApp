package com.simrnpuri.newsapp.data.models

import com.simrnpuri.newsapp.data.models.Article

data class News(val totalResults:Int,val articles : List<Article>) {
}
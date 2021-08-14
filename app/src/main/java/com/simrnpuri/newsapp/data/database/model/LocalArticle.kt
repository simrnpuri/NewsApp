package com.simrnpuri.newsapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.simrnpuri.newsapp.data.domain.Article

@Entity
data class LocalArticle(@PrimaryKey
                        val id: Int?,val author:String?, val title:String?, val description:String?, val url:String?, val urlToImage:String?, var isFav: Boolean){
    fun toDomain():Article{
        return Article(author="",title="",description="",url="",urlToImage="")
    }

}
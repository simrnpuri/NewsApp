package com.simrnpuri.newsapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.simrnpuri.newsapp.data.database.model.LocalArticle

@Dao
interface ArticleDao {
    @Query("SELECT * FROM LocalArticle")
    fun getArticleList(): LiveData<List<LocalArticle>>

    @Query("SELECT * FROM LocalArticle WHERE isFav")
    fun getFavedArticleList(): LiveData<List<LocalArticle>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(articles: List<LocalArticle>)

    @Update
    fun update(article: LocalArticle)
}
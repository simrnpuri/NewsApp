package com.simrnpuri.newsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.simrnpuri.newsapp.data.database.model.LocalArticle

@Database(entities = [LocalArticle::class], version = 1)
abstract class ArticleDatabse: RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao
}
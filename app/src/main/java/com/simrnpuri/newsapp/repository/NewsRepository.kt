package com.simrnpuri.newsapp.repository

import androidx.lifecycle.LiveData
import com.simrnpuri.newsapp.data.database.ArticleDatabse
import com.simrnpuri.newsapp.data.database.model.LocalArticle
import com.simrnpuri.newsapp.data.domain.News
import com.simrnpuri.newsapp.network.NewsInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class NewsRepository(private val apiService: NewsInterface,
                     private val articleDatabase: ArticleDatabse) {

    val newsListLiveData: LiveData<List<LocalArticle>> =
        articleDatabase.getArticleDao().getArticleList()

    suspend fun loadArticles() {
        val result: News? = try {
            apiService.getHeadlines("CA",1)
        } catch (e: HttpException) {
            null
        } catch (e: IOException) {
            null
        } catch (e: Exception) {
            null
        }

        result?.let {
            withContext(Dispatchers.IO) {
                articleDatabase.getArticleDao().insertAll(it.articles.map { it.toLocal() })
            }
        }
    }


    suspend fun updateArticle(article: LocalArticle) {
        withContext(Dispatchers.IO) {
            articleDatabase.getArticleDao().update(article)
        }
    }
}
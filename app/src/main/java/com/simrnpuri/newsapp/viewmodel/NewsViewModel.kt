package com.simrnpuri.newsapp.viewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.simrnpuri.newsapp.data.database.model.LocalArticle
import com.simrnpuri.newsapp.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: NewsRepository,
    handle: SavedStateHandle
): ViewModel() {

    init {
        viewModelScope.launch {
            repository.loadArticles()
        }
    }

    fun onFaveClick(article: LocalArticle) {
        viewModelScope.launch {
            article.isFav = !article.isFav
            repository.updateArticle(
                article
            )
        }
    }
}


class NewsViewModelFactory(
    private val repository: NewsRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle?
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(repository, handle) as T
        }

        throw IllegalArgumentException("Invalid ViewModel")
    }
}
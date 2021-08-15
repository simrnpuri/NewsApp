package com.simrnpuri.newsapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.simrnpuri.newsapp.MyApp
import com.simrnpuri.newsapp.R
import com.simrnpuri.newsapp.data.database.model.LocalArticle
import com.simrnpuri.newsapp.data.domain.Article
import com.simrnpuri.newsapp.repository.NewsRepository
import com.simrnpuri.newsapp.viewmodel.NewsViewModel
import com.simrnpuri.newsapp.viewmodel.NewsViewModelFactory
import kotlinx.android.synthetic.main.fragment_all_news.*


class NewsListFragment : Fragment() {


    lateinit var adapter: NewsAdapter
    private var articles = ArrayList<LocalArticle>()


    private val viewModel by lazy {
        val apiService = (requireActivity().application as MyApp).serviceLocator.apiService
        val database = (requireActivity().application as MyApp).serviceLocator.articleDatabase
        val repository = NewsRepository(apiService, database)
        val factory = NewsViewModelFactory(repository, this, arguments)
        ViewModelProvider(this, factory).get(NewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = NewsAdapter(object : NewsAdapter.OnArticleClickListener {
            override fun onFavCLick(article: LocalArticle) {
                viewModel.onFaveClick(article)
            }

            override fun onArticleClick(url: String) {
            }

        })
        newsList.adapter = adapter

        viewModel.newsList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.filter { article ->
                article.author.contains(etSearch.text.toString(), true)  || article.description.contains(etSearch.text.toString(), true)
                        || article.title.contains(etSearch.text.toString(), true)})
            articles.addAll(it)
        })

        etSearch.doAfterTextChanged {
            if (it.toString().isNotEmpty()) {
                val filteredList = articles.filter { article ->
                    article.author.contains(it.toString(), true)  || article.description.contains(it.toString(), true)
                            || article.title.contains(it.toString(), true)
                }
                adapter.submitList(filteredList)
            }else {
                adapter.submitList(articles)
            }

        }
    }
}
package com.simrnpuri.newsapp.views
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.simrnpuri.*
import com.simrnpuri.newsapp.R
import com.simrnpuri.newsapp.data.MainTab
import com.simrnpuri.newsapp.data.domain.Article
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_view_pager.adapter = MainViewPagerAdapter(this)

        TabLayoutMediator(tab_layout, main_view_pager) {tab, position ->
            tab.text = MainTab.values()[position].title
        }.attach()
    }
}
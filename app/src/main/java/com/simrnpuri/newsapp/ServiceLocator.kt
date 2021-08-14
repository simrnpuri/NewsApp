package com.simrnpuri.newsapp

import android.content.Context
import androidx.room.Room
import com.simrnpuri.newsapp.data.database.ArticleDatabse
import com.simrnpuri.newsapp.network.BASE_URL
import com.simrnpuri.newsapp.network.NewsInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceLocator(applicationContext: Context) {


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: NewsInterface = retrofit.create(NewsInterface::class.java)
    val pokemonDatabase = Room.databaseBuilder(
        applicationContext,
        ArticleDatabse::class.java,
        "articles"
    ).fallbackToDestructiveMigration()
        .build()
}
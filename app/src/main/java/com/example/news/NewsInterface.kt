package com.example.news

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=in&apiKey=5bc8f27a8cd74ccbbf7a5d678cb7b9cd
const val BASE_URl = "https://newsapi.org/"
const val URL_KEY = "5bc8f27a8cd74ccbbf7a5d678cb7b9cd"

interface NewsInterface {
    @GET("v2/top-headlines?apikey=$URL_KEY")
    fun getHeadLines(
        @Query("country") country: String,
    ): Call<News>

    object NewsService{

        var newsInstance:NewsInterface

        init {
            val retrofit=Retrofit.Builder()
                .baseUrl(BASE_URl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            newsInstance = retrofit.create(NewsInterface::class.java)
        }

    }
}
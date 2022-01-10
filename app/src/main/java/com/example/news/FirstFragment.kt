package com.example.news

import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import androidx.viewpager2.widget.ViewPager2
import com.example.news.adapters.NewsAdapter
import com.example.news.utils.DepthPageTransformer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Context
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView


class FirstFragment : Fragment() {
    lateinit var adapter: NewsAdapter
    private lateinit var recyclerView: ViewPager2
    private lateinit var frameLayout: FrameLayout
    private lateinit var loadingIcon: AppCompatImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contextThemeWrapper: Context = ContextThemeWrapper(activity, R.style.Theme_News)
        val localInflater = inflater.cloneInContext(contextThemeWrapper)
        return localInflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.m_recyclerView)
        frameLayout = view.findViewById(R.id.m_frame)
        loadingIcon = view.findViewById(R.id.loading_icon)
        getNews()
    }

    private fun getNews() {
        loadingIcon.visibility = View.VISIBLE
        val news = NewsInterface.NewsService.newsInstance.getHeadLines("in")
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                loadingIcon.visibility = View.GONE
                val mNews = response.body() ?: News(arrayListOf(), 0)
                Log.e("nitesh", mNews.toString())
                if (mNews.totalResults == 0) Toast.makeText(
                    requireContext(),
                    "Sorry no news available at this time",
                    Toast.LENGTH_LONG
                ).show()
                adapter = NewsAdapter(mNews.articles as ArrayList<Article>, activity as AppCompatActivity)

                recyclerView.adapter = adapter
                recyclerView.setPageTransformer(DepthPageTransformer())


                frameLayout.setBackgroundColor(Color.BLACK)


            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(activity, "fail", Toast.LENGTH_SHORT).show()
                loadingIcon.visibility = View.GONE
            }

        })


    }


}
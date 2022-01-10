package com.example.news.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.*

import com.example.news.utils.OnSwipeTouchListener

const val URL = "url"

class NewsAdapter(private val list:ArrayList<Article>, private val activity: AppCompatActivity ):RecyclerView.Adapter<NewsAdapter.Newses>() {
    inner class Newses(itemView: View):RecyclerView.ViewHolder(itemView){
        val image:ImageView=itemView.findViewById(R.id.n_image)
        val tittle:TextView=itemView.findViewById(R.id.n_tittle)
        val tittle2:TextView=itemView.findViewById(R.id.head)
        val mDescription:TextView=itemView.findViewById(R.id.n_discription)
        val mName:TextView=itemView.findViewById(R.id.n_name)
        val mCard:CardView=itemView.findViewById(R.id.cardView2)
        val bImage:ImageView=itemView.findViewById(R.id.b_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Newses=
        Newses(LayoutInflater.from(parent.context).inflate(R.layout.activity_detail,parent,false))

    override fun onBindViewHolder(holder: Newses, position: Int) {
        val currentItem = list[position]
        Glide.with(activity).load(currentItem.urlToImage).into(holder.image)
        Glide.with(activity).load(currentItem.urlToImage).override(12,12).into(holder.bImage)

        val string = currentItem.content?.replace("\n","")
        val str=string?.split(" ")
        holder.tittle.text = currentItem.title
        holder.mDescription.text = currentItem.description
        holder.mName.text = currentItem.source.name
        var str2="";
        if(str?.size?:0<5){
            for(i in 0..1){
                str2 += (str?.get(i) ?: "") +" "
            }
        }
        else{for(i in 0..4){
            str2 += (str?.get(i) ?: "") +" "
        }}

        holder.tittle2.text=str2+"...."
        holder.bImage.setOnClickListener {
            val bundle=Bundle()
            bundle.putString(URL,currentItem.url)
            val fragment2=SecondFragment()
            fragment2.arguments=bundle
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2,fragment2).addToBackStack("null").commit()
        }

    }
    override fun getItemCount(): Int =list.size
}
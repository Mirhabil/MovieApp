package com.example.letterboxd.presentation.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letterboxd.R
import com.example.letterboxd.databinding.PopularliststhismonthdesignBinding
import com.example.letterboxd.domain.model.GroupPopularListsThisMonthModel

class NewGroupedAdapter: PagingDataAdapter<GroupPopularListsThisMonthModel,NewGroupedViewHolder>(GroupPopularListsThisMonthModel.diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewGroupedViewHolder {
        return NewGroupedViewHolder(binding = PopularliststhismonthdesignBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: NewGroupedViewHolder, position: Int) {

        val binding=holder.binding
        val items=getItem(position)

        if (items==null){
            return
        }

        Log.e("GroupResponseeee",items?.movie1.toString())
        Glide.with(binding.imageView5)
            .load("https://image.tmdb.org/t/p/w500"+items.movie1.classImage1).error(R.drawable.ic_launcher_background).into(binding.imageView5)
        Glide.with(binding.imageView6).load("https://image.tmdb.org/t/p/w500"+items.movie2.classImage2).into(binding.imageView6)
        Glide.with(binding.imageView7).load("https://image.tmdb.org/t/p/w500"+items.movie3.classImage3).into(binding.imageView7)
        Glide.with(binding.imageView8).load("https://image.tmdb.org/t/p/w500"+items.movie4.classImage4).into(binding.imageView8)

    }
}

class NewGroupedViewHolder(val binding:PopularliststhismonthdesignBinding):RecyclerView.ViewHolder(binding.root)
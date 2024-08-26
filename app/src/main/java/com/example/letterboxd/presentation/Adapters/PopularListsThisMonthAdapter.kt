package com.example.letterboxd.presentation.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letterboxd.R
import com.example.letterboxd.databinding.PopularliststhismonthdesignBinding
import com.example.letterboxd.domain.model.GroupPopularListsThisMonthModel

class PopularListsThisMonthAdapter(
    val onClick:(GroupPopularListsThisMonthModel)-> Unit
):RecyclerView.Adapter<PopularListsThisMonthAdapter.PopularListsThisMonthViewHolder>() {

    class PopularListsThisMonthViewHolder(val binding:PopularliststhismonthdesignBinding):RecyclerView.ViewHolder(binding.root)

    var adapterResponse:List<GroupPopularListsThisMonthModel> = emptyList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularListsThisMonthViewHolder {
        return PopularListsThisMonthViewHolder(binding = PopularliststhismonthdesignBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return adapterResponse.size
    }

    override fun onBindViewHolder(holder: PopularListsThisMonthViewHolder, position: Int) {
        val binding=holder.binding
        val items=adapterResponse[position]

        binding.root.setOnClickListener {

            onClick(items)
        }

        Log.e("GroupResponseeee",items.movie1.toString())
        Glide.with(binding.imageView5)
            .load("https://image.tmdb.org/t/p/w500"+items.movie1.classImage1).error(R.drawable.ic_launcher_background).into(binding.imageView5)
        Glide.with(binding.imageView6).load("https://image.tmdb.org/t/p/w500"+items.movie2.classImage2).into(binding.imageView6)
        Glide.with(binding.imageView7).load("https://image.tmdb.org/t/p/w500"+items.movie3.classImage3).into(binding.imageView7)
        Glide.with(binding.imageView8).load("https://image.tmdb.org/t/p/w500"+items.movie4.classImage4).into(binding.imageView8)
    }

    fun getPopularListThisMonthList(newAdapterResponse:List<GroupPopularListsThisMonthModel>){

        adapterResponse=newAdapterResponse
        Log.e("GroupResponseeee",adapterResponse.toString())
        notifyDataSetChanged()
    }
}


package com.example.letterboxd.presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letterboxd.data.remote.model.PopularMoviesResponse.Result
import com.example.letterboxd.databinding.PopularfilmsthismonthdesignBinding
import com.example.letterboxd.domain.model.GroupPopularListsThisMonthModel
import com.example.letterboxd.domain.model.ParcalableMovieItem
import com.example.letterboxd.domain.model.PopularFilmsThisMonthModel

class PopularFilmsThisMonthAdapter(
    //val onClick:(PopularFilmsThisMonthModel)->Unit
    val onClick:(Int)->Unit



):RecyclerView.Adapter<PopularFilmsThisMonthAdapter.PopularFilmsThisMonthViewHolder>() {

    class PopularFilmsThisMonthViewHolder(val binding:PopularfilmsthismonthdesignBinding):RecyclerView.ViewHolder(binding.root)
    var adapterList:List<PopularFilmsThisMonthModel> = emptyList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularFilmsThisMonthViewHolder {
        return PopularFilmsThisMonthViewHolder(binding= PopularfilmsthismonthdesignBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }

    override fun onBindViewHolder(holder: PopularFilmsThisMonthViewHolder, position: Int) {
        val binding=holder.binding
        val items=adapterList[position]

        binding.root.setOnClickListener {

            //onClick(items)
            onClick(items.movieID)
        }

        Glide.with(binding.imageView4).load("https://image.tmdb.org/t/p/w500"+items.image).into(binding.imageView4)
    }

    fun getAdapterList(newAdapterList:List<PopularFilmsThisMonthModel>){

        adapterList=newAdapterList
        notifyDataSetChanged()
    }


}


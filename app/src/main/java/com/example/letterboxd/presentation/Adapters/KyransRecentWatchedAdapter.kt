package com.example.letterboxd.presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letterboxd.databinding.KayransrecentwatchedBinding
import com.example.letterboxd.domain.model.KyransRecentWatchedModel

class KyransRecentWatchedAdapter(
    val onClick:(Int)->Unit
):RecyclerView.Adapter<KyransRecentWatchedAdapter.KyransRecentWatchedViewHolder>() {
    class KyransRecentWatchedViewHolder(val binding:KayransrecentwatchedBinding):RecyclerView.ViewHolder(binding.root)
    var adapterList:List<KyransRecentWatchedModel> = emptyList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KyransRecentWatchedViewHolder {
        return KyransRecentWatchedViewHolder(binding = KayransrecentwatchedBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }

    override fun onBindViewHolder(holder: KyransRecentWatchedViewHolder, position: Int) {
        val binding=holder.binding
        val items=adapterList[position]

        Glide.with(binding.imageViewCopy6).load("https://image.tmdb.org/t/p/w500"+items.poster).into(binding.imageViewCopy6)
        binding.ratingBar.rating=((items.rating)/2).toFloat()

        binding.root.setOnClickListener {

            onClick(items.movieId)
        }
    }

    fun getAdapterList(newAdapterList:List<KyransRecentWatchedModel>){

        adapterList=newAdapterList
        notifyDataSetChanged()
    }
}

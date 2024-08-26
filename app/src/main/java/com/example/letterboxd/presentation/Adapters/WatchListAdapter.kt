package com.example.letterboxd.presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letterboxd.data.local.WatchlistDatabase.WatchlistEntity
import com.example.letterboxd.databinding.WatchlistdesignBinding
import kotlin.random.Random


class WatchListAdapter(
    val onClick:(Int)->Unit
): RecyclerView.Adapter<WatchListAdapter.WatchListViewHolder>() {
    class WatchListViewHolder(val binding: WatchlistdesignBinding): RecyclerView.ViewHolder(binding.root)

    var adapterList:List<WatchlistEntity> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListViewHolder {
        return WatchListViewHolder(binding = WatchlistdesignBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }

    override fun onBindViewHolder(holder: WatchListViewHolder, position: Int) {
        val binding=holder.binding
        val items=adapterList[position]

        Glide.with(binding.imageView30).load("https://image.tmdb.org/t/p/w500"+items.imageUrl).into(binding.imageView30)
        binding.textView46movieName.text=items.movieName
        binding.ratingBar8.rating=items.rating.toFloat()/2
        binding.textView49movieYear.text=items.moiveYear
        binding.textView48rating.text=(items.rating/2).toString()
        binding.textView50movieLike.text=getRandomIntInRange(100,1000).toString()

        binding.root.setOnClickListener {

            onClick(items.id)
        }


    }

    fun getAdapterList(newAdapterList:List<WatchlistEntity>){

        adapterList=newAdapterList
        notifyDataSetChanged()
    }


    fun getRandomIntInRange(min: Int, max: Int): Int {
        require(min < max) { "Max must be greater than min" }
        return Random.nextInt(min, max + 1)
    }
}
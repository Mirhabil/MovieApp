package com.example.letterboxd.presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letterboxd.databinding.ExplorepagedesignBinding
import com.example.letterboxd.domain.model.PopularFilmsThisMonthModel

class ExplorePageAdapter(

    val onClick:(Int) ->Unit
):RecyclerView.Adapter<ExplorePageAdapter.ExplorePageViewHolder>() {
    class ExplorePageViewHolder(val binding:ExplorepagedesignBinding):RecyclerView.ViewHolder(binding.root)

    //var adapterList:List<PopularFilmsThisMonthModel> = emptyList()
    private var adapterList: MutableList<PopularFilmsThisMonthModel> = mutableListOf()

    fun addItems(newItems: List<PopularFilmsThisMonthModel>) {
        val currentSize = adapterList.size
        adapterList.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExplorePageViewHolder {
        return ExplorePageViewHolder(binding = ExplorepagedesignBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }

    override fun onBindViewHolder(holder: ExplorePageViewHolder, position: Int) {
        val binding=holder.binding
        val items=adapterList[position]

        Glide.with(binding.imageView28).load("https://image.tmdb.org/t/p/w500"+ items.image).into(binding.imageView28)
        binding.textView43.text=items.moviname

        binding.imageView28.setOnClickListener {

            onClick(items.movieID)


        }


    }

//    fun getAdapterList(newAdapterList:List<PopularFilmsThisMonthModel>){
//
//        adapterList=newAdapterList
//        notifyDataSetChanged()
//    }


}

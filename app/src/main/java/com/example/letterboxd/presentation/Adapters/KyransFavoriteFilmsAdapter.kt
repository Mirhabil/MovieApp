package com.example.letterboxd.presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letterboxd.data.local.ImageDatabase.PosterEntity
import com.example.letterboxd.databinding.KyransfavoritefilmsdesignBinding

class KyransFavoriteFilmsAdapter(
    val onClick:(PosterEntity)->Unit
):RecyclerView.Adapter<KyransFavoriteFilmsAdapter.KyransFavoriteFilmsViewHolder>() {
    class KyransFavoriteFilmsViewHolder(val binding:KyransfavoritefilmsdesignBinding):RecyclerView.ViewHolder(binding.root)
    var adapterList:List<PosterEntity> = emptyList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KyransFavoriteFilmsViewHolder {
        return KyransFavoriteFilmsViewHolder(binding = KyransfavoritefilmsdesignBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }

    override fun onBindViewHolder(holder: KyransFavoriteFilmsViewHolder, position: Int) {
        val binding=holder.binding
        val items=adapterList[position]

        Glide.with(binding.imageViewCopy4).load("https://image.tmdb.org/t/p/w500"+items.imageURL).into(binding.imageViewCopy4)

        binding.imageViewCopy4.setOnClickListener {

            onClick(items)


        }

    }

    fun getAdapterList(newAdapterList:List<PosterEntity>){

        adapterList=newAdapterList
        notifyDataSetChanged()
    }
}

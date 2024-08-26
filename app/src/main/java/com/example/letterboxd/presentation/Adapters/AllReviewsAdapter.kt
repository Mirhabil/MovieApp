package com.example.letterboxd.presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letterboxd.R
import com.example.letterboxd.databinding.AllreviewsdesignBinding
import com.example.letterboxd.domain.model.AllReviewsModel

class AllReviewsAdapter:RecyclerView.Adapter<AllReviewsAdapter.AllReviewsViewHolder>() {
    class AllReviewsViewHolder(val binding: AllreviewsdesignBinding):RecyclerView.ViewHolder(binding.root)

    var adapterList:List<AllReviewsModel> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllReviewsViewHolder {
        return AllReviewsViewHolder(binding = AllreviewsdesignBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }

    override fun onBindViewHolder(holder: AllReviewsViewHolder, position: Int) {
        val binding=holder.binding
        val items=adapterList[position]

        Glide.with(binding.imageViewProfileAR).load("https://image.tmdb.org/t/p/w500"+items.profileImage).placeholder(
            R.drawable.userprofilefoto).into(binding.imageViewProfileAR)
        binding.textViewPersonNameAR.text=items.personName
        binding.textViewReviewAR.text=items.review

        binding.ratingBar5.rating=items.rating.toFloat()
    }

    fun getAdapterList(newAdapterList:List<AllReviewsModel>){

        adapterList=newAdapterList.reversed()
        notifyDataSetChanged()
    }
}


package com.example.letterboxd.presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letterboxd.R
import com.example.letterboxd.data.local.ReviewsDatabase.ReviewsEntity
import com.example.letterboxd.databinding.KyransrecentrevieweddesignBinding
import com.example.letterboxd.databinding.RecentfriendsreviewdesignBinding

class KyransRecentReviewedAdapter(
    val profilePicture:String?
):RecyclerView.Adapter<KyransRecentReviewedAdapter.KyransRecentReviewdViewHolder>() {
    class KyransRecentReviewdViewHolder(val binding:RecentfriendsreviewdesignBinding):RecyclerView.ViewHolder(binding.root)

    var adapterList:List<ReviewsEntity> = emptyList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KyransRecentReviewdViewHolder {
        return KyransRecentReviewdViewHolder(binding = RecentfriendsreviewdesignBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }

    override fun onBindViewHolder(holder: KyransRecentReviewdViewHolder, position: Int) {
        val binding=holder.binding
        val items=adapterList[position]

        Glide.with(binding.imageViewPosterRecent).load("https://image.tmdb.org/t/p/w500"+ items.imageUrl).into(binding.imageViewPosterRecent)
        binding.textViewMovieDecriptionRecent.text=items.review
        binding.textViewMovieNameRecent.text=items.movieName
        binding.textViewMovieYear.text=items.movieYear.split("-").first()
        binding.ratingBar4.rating=items.rating.toFloat()

        Glide.with(binding.imageViewProfileRecent).load(profilePicture).error(R.drawable.userprofilefoto).into(binding.imageViewProfileRecent)
    }

    fun getAdapterList(newAdapterList:List<ReviewsEntity>){

        adapterList=newAdapterList.reversed()
        notifyDataSetChanged()


    }
}


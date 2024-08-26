package com.example.letterboxd.presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letterboxd.databinding.RecentfriendsreviewdesignBinding
import com.example.letterboxd.domain.model.RecentFriendsReviewModel

class RecentFriendsReviewAdapter(

    val onClick:(RecentFriendsReviewModel)->Unit
):RecyclerView.Adapter<RecentFriendsReviewAdapter.RecentFriendsreviewViewHolder>() {

    class RecentFriendsreviewViewHolder(val binding:RecentfriendsreviewdesignBinding):RecyclerView.ViewHolder(binding.root)

    var adapterList:List<RecentFriendsReviewModel> = emptyList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentFriendsreviewViewHolder {
        return RecentFriendsreviewViewHolder(binding= RecentfriendsreviewdesignBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }

    override fun onBindViewHolder(holder: RecentFriendsreviewViewHolder, position: Int) {
        val binding=holder.binding
        val items=adapterList[position]

        Glide.with(binding.imageViewProfileRecent).load("https://image.tmdb.org/t/p/w500"+items.profileImage).into(binding.imageViewProfileRecent)
        Glide.with(binding.imageViewPosterRecent).load("https://image.tmdb.org/t/p/w500"+items.postImage).into(binding.imageViewPosterRecent)
        binding.textViewMovieNameRecent.text=items.movieName
        binding.textViewMovieDecriptionRecent.text=items.movieDescription
        binding.textViewMovieYear.text=items.movieYear
        binding.textViewMovieActor.text=items.movieActor

        binding.ratingBar4.rating=items.rating.toFloat()

        binding.root.setOnClickListener {

            onClick(items)
        }
    }

    fun getAdapterList(newAdapterList:List<RecentFriendsReviewModel>){

        adapterList=newAdapterList
        notifyDataSetChanged()
    }

}

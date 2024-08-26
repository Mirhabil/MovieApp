package com.example.letterboxd.presentation.Adapters

import android.app.appsearch.SearchResult
import android.media.browse.MediaBrowser
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letterboxd.data.remote.model.SearchResponse.ResultSearch
import com.example.letterboxd.databinding.ExplorepagedesignBinding

class SearchAndExploreAdapter(val function: (ResultSearch) -> Unit) : PagingDataAdapter<ResultSearch, SearchAndExploreAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val binding: ExplorepagedesignBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultSearch>() {
            override fun areItemsTheSame(oldItem: ResultSearch, newItem: ResultSearch): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ResultSearch, newItem: ResultSearch): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding

        if (item != null) {
            binding.textView43.text = item.title
            Glide.with(binding.imageView28).load("https://image.tmdb.org/t/p/w500" + item.poster_path).into(binding.imageView28)
            binding.textView46.text=(item.vote_average/2).toString().substring(0,3)
            binding.ratingBar9.rating=(item.vote_average/2f).toFloat()
        }

        binding.root.setOnClickListener {
            if (item != null) {
                function(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ExplorepagedesignBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}
package com.example.letterboxd.presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letterboxd.R
import com.example.letterboxd.databinding.CastscrewsdesignBinding
import com.example.letterboxd.domain.model.CastsAndCrewsModel

class CastsAndCrewsAdapter(
    val onClick:(Int)->Unit
):RecyclerView.Adapter<CastsAndCrewsAdapter.CastsAndCrewsViewHolder>() {
    class CastsAndCrewsViewHolder(val binding:CastscrewsdesignBinding):RecyclerView.ViewHolder(binding.root)

    var adapterList:List<CastsAndCrewsModel> = emptyList()
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastsAndCrewsViewHolder {
        return CastsAndCrewsViewHolder(binding = CastscrewsdesignBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }

    override fun onBindViewHolder(holder: CastsAndCrewsViewHolder, position: Int) {
        val binding=holder.binding
        val items=adapterList[position]

        Glide.with(binding.imageView18).load("https://image.tmdb.org/t/p/w500"+items.image).placeholder(
            R.drawable.userprofilefoto).into(binding.imageView18)

        binding.root.setOnClickListener {

            onClick(items.personId)


        }

    }

    fun getAdapterList(newAdpaterList:List<CastsAndCrewsModel>){

        adapterList=newAdpaterList
        notifyDataSetChanged()


    }
}

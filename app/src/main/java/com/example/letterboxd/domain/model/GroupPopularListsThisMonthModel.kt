package com.example.letterboxd.domain.model

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.example.letterboxd.presentation.Adapters.PopularListsThisMonthAdapter
import kotlinx.parcelize.Parcelize

@Parcelize
class GroupPopularListsThisMonthModel(

    val movie1:PopularListsThisMonthModel,
    val movie2:PopularListsThisMonthModel,
    val movie3:PopularListsThisMonthModel,
    val movie4:PopularListsThisMonthModel,
    val movieIds: List<Int>
):Parcelable {
    companion object{

        val diffCallBack=object:DiffUtil.ItemCallback<GroupPopularListsThisMonthModel>(){

            override fun areItemsTheSame(
                oldItem: GroupPopularListsThisMonthModel,
                newItem: GroupPopularListsThisMonthModel
            ): Boolean {
                return oldItem==newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: GroupPopularListsThisMonthModel,
                newItem: GroupPopularListsThisMonthModel
            ): Boolean {
                return oldItem==newItem
            }
        }
    }
}
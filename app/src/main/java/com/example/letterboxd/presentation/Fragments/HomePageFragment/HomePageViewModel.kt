package com.example.letterboxd.presentation.Fragments.HomePageFragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letterboxd.domain.model.GroupPopularListsThisMonthModel
import com.example.letterboxd.domain.model.PopularFilmsThisMonthModel
import com.example.letterboxd.domain.model.RecentFriendsReviewModel
import com.example.letterboxd.domain.repository.PopularMoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(val popularMoviesRepo:PopularMoviesRepo):ViewModel() {

    val responseFlow= MutableStateFlow<List<PopularFilmsThisMonthModel>>(emptyList())

    val recentFriendsReviewResponseFlow= MutableStateFlow<List<RecentFriendsReviewModel>>(emptyList())

    val popularListsThisMonthFlow= MutableStateFlow<List<GroupPopularListsThisMonthModel>>(emptyList())



    init {
        getPopularMoviesRepoResponse()
    }

    init {
        getRecentFriendsReviewResponse()
    }

    init {
        getPopularListsThisMonth()
    }

    fun getPopularMoviesRepoResponse(){

        viewModelScope.launch {

            val response=popularMoviesRepo.getPopularMovies(1)
            Log.e("Responseeeee",response.toString())
            responseFlow.emit(response)


        }

    }

    /*
    fun getRecentFriendsReviewResponse(){

        viewModelScope.launch {

            val recentFriendsReviewResponse=popularMoviesRepo.getRecentFriendsReviewModel()
            Log.e("New Responseeee",recentFriendsReviewResponse.toString())
            recentFriendsReviewResponseFlow.emit(recentFriendsReviewResponse)

        }

    }

     */

    fun getRecentFriendsReviewResponse(){

        viewModelScope.launch {

            val recentFriendsReviewResponse=popularMoviesRepo.getPeopleLists()
            recentFriendsReviewResponseFlow.emit(recentFriendsReviewResponse)
        }
    }

    fun getPopularListsThisMonth(){

        viewModelScope.launch {

            val popularListsThisMonthResponse=popularMoviesRepo.getGroupPopularListsThisMonth(1)
            Log.e("GroupResponseeee",popularListsThisMonthResponse.toString())
            popularListsThisMonthFlow.emit(popularListsThisMonthResponse)


        }
    }


}
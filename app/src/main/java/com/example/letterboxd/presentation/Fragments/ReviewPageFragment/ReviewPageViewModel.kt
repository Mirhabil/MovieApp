package com.example.letterboxd.presentation.Fragments.ReviewPageFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letterboxd.domain.model.PopularFilmsThisMonthModel
import com.example.letterboxd.domain.repository.PopularMoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewPageViewModel @Inject constructor(val popularMoviesRepo:PopularMoviesRepo):ViewModel() {



    val responseFlow=MutableStateFlow<PopularFilmsThisMonthModel?>(null)

    fun getPopularFilms(movieId:Int){

        viewModelScope.launch {

            responseFlow.update {

                popularMoviesRepo.getDetailsResponse(movieId)

            }




        }
    }
}
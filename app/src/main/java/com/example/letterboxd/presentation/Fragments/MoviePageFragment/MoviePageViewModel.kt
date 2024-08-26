package com.example.letterboxd.presentation.Fragments.MoviePageFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letterboxd.data.remote.model.DetailsResponse.DetailsResponse
import com.example.letterboxd.data.remote.model.ImagesResponse.ImagesResponse
import com.example.letterboxd.domain.model.CastsAndCrewsModel
import com.example.letterboxd.domain.model.ImagesModel
import com.example.letterboxd.domain.model.PopularFilmsThisMonthModel
import com.example.letterboxd.domain.repository.PopularMoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviePageViewModel @Inject constructor(val popularMoviesRepo: PopularMoviesRepo):ViewModel() {

    val castsResponseFlow= MutableStateFlow<List<CastsAndCrewsModel>>(emptyList())
    val crewsResponseFlow= MutableStateFlow<List<CastsAndCrewsModel>>(emptyList())
    val imagesResponseFlow= MutableStateFlow<List<ImagesModel>>(emptyList())
    val detailsFlow= MutableStateFlow<PopularFilmsThisMonthModel?>(null)

    fun getMovieId(movieId:Int){

        viewModelScope.launch {

            val response=popularMoviesRepo.getCasts(movieId)
            castsResponseFlow.update { response }


        }


    }

    fun getCrewsMovieId(movieID:Int){

        viewModelScope.launch {

            val response=popularMoviesRepo.getCrews(movieID)
            crewsResponseFlow.update { response }

        }
    }

    fun getImagesMovieId(movieID:Int){

        viewModelScope.launch {

            val response=popularMoviesRepo.getMoviePageBackgroundImage(movieID)
            imagesResponseFlow.update { response }


        }
    }

    fun getDetailsResponseMovieId(movieId: Int){

        var response:PopularFilmsThisMonthModel?=null

        viewModelScope.launch {

            response=popularMoviesRepo.getDetailsResponse(movieId)

            detailsFlow.update { response}
        }
    }


}
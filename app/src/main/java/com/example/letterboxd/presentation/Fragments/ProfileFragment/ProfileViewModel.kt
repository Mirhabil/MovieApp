package com.example.letterboxd.presentation.Fragments.ProfileFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letterboxd.domain.model.KyransRecentWatchedModel
import com.example.letterboxd.domain.repository.PopularMoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(val repository:PopularMoviesRepo):ViewModel() {

    val responseFlow=MutableStateFlow<List<KyransRecentWatchedModel>>(emptyList())

    init {
        getResponseFlowData()
    }

    fun getResponseFlowData(){

        viewModelScope.launch {

            responseFlow.update { repository.getNowPlayingResponse() }
        }


    }
}
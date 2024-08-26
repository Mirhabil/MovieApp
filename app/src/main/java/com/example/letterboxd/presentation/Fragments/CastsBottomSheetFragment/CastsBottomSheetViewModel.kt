package com.example.letterboxd.presentation.Fragments.CastsBottomSheetFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letterboxd.data.remote.model.PersonIdResponse.PersonIdResponse
import com.example.letterboxd.domain.repository.PopularMoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CastsBottomSheetViewModel @Inject constructor(val popularMoviesRepo:PopularMoviesRepo):ViewModel() {

    val response= MutableStateFlow<PersonIdResponse?>(null)


    fun getResponse(personId:Int){

        viewModelScope.launch {

            response.update { popularMoviesRepo.getPersonIdResponse(personId) }
        }
    }
}
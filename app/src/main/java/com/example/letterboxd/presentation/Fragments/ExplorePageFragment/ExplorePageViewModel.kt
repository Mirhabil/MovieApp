package com.example.letterboxd.presentation.Fragments.ExplorePageFragment


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.letterboxd.domain.model.PopularFilmsThisMonthModel
import com.example.letterboxd.domain.repository.PopularMoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExplorePageViewModel @Inject constructor(val popularMoviesRepo:PopularMoviesRepo):ViewModel() {


    private val _currentPage = MutableStateFlow(1)
    val currentPage: StateFlow<Int> = _currentPage
    //val responseFlow=MutableStateFlow<List<PopularFilmsThisMonthModel>>(emptyList())
    private val _responseFlow = MutableStateFlow<List<PopularFilmsThisMonthModel>>(emptyList())
    val responseFlow: StateFlow<List<PopularFilmsThisMonthModel>> = _responseFlow

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isLastPage = MutableStateFlow(false)
    val isLastPage: StateFlow<Boolean> = _isLastPage

    init {
        fetchNextPage()
    }

    fun fetchNextPage() {
        if (_isLoading.value || _isLastPage.value) return // Avoid multiple fetch requests or when on the last page

        viewModelScope.launch {
            _isLoading.value = true // Set loading state

            try {
                val nextPage = currentPage.value
                val newData = popularMoviesRepo.getPopularMovies(page = nextPage) // Replace with your actual data fetching logic
                _responseFlow.value += newData
                _currentPage.value++

                // Example check for last page
                _isLastPage.value = newData.isEmpty() // Assuming empty newData signifies the last page

            } catch (e: Exception) {
                // Handle error, e.g., log or show error message
            } finally {
                _isLoading.value = false // Reset loading state
            }
        }
    }



//    init {
//        getExploreResponse()
//    }
//
//    fun getExploreResponse(){
//
//        viewModelScope.launch {
//
//            responseFlow.update {  popularMoviesRepo.getPopularMovies(1) }
//
//
//        }
//    }
}
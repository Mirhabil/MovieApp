package com.example.letterboxd.presentation.Fragments.ExplorePageFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.letterboxd.data.remote.api.PopularMovies
import com.example.letterboxd.data.remote.model.SearchResponse.ResultSearch
import com.example.letterboxd.paging.SearchPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class ExplorePageViewModelNew @Inject constructor(
    private val apiService: PopularMovies
) : ViewModel() {
    private val searchQuery = MutableStateFlow<String?>(null)

    fun setSearchQuery(query: String?) {
        searchQuery.value = query
    }

    val pagedData: Flow<PagingData<ResultSearch>> = searchQuery.flatMapLatest { query ->
        Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { SearchPagingSource(apiService, query, "fa22bdcd3f461d2aec53c6b2b47e85e7") }
        ).flow
    }.cachedIn(viewModelScope)
}
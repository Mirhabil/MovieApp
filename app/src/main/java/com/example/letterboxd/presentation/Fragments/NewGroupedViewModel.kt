package com.example.letterboxd.presentation.Fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.letterboxd.paging.GroupedPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewGroupedViewModel @Inject constructor(val pagingSource:GroupedPagingSource):ViewModel() {

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20)
    ) {
        pagingSource
    }.flow
        .cachedIn(viewModelScope)


}
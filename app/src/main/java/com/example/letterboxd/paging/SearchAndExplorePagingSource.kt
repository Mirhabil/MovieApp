package com.example.letterboxd.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.letterboxd.data.remote.model.SearchResponse.ResultSearch
import com.example.letterboxd.domain.model.PopularFilmsThisMonthModel
import com.example.letterboxd.domain.repository.PopularMoviesRepo
//
//class SearchAndExplorePagingSource(
//    val backend: PopularMoviesRepo,
//    val query: String,
//    val apiKey:String
//) : PagingSource<Int, PopularFilmsThisMonthModel>() {
//    override suspend fun load(
//        params: LoadParams<Int>
//    ): LoadResult<Int, PopularFilmsThisMonthModel> {
//       return try {
//            // Start refresh at page 1 if undefined.
//            val nextPageNumber = params.key ?: 1
//            val response = if (query.isNullOrEmpty()){
//                backend.getExplorePageFilms(apiKey,nextPageNumber)
//            } else {
//                backend.searchFilms(apiKey,query,nextPageNumber)
//            }
//            LoadResult.Page(
//                data = response,
//                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
//                nextKey = if (nextPageNumber == response.) null else nextPageNumber + 1
//            )
//        } catch (e: Exception) {
//
//           LoadResult.Error(e)
//            // Handle errors in this block and return LoadResult.Error for
//            // expected errors (such as a network failure).
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, ResultSearch>): Int? {
//        // Try to find the page key of the closest page to anchorPosition from
//        // either the prevKey or the nextKey; you need to handle nullability
//        // here.
//        //  * prevKey == null -> anchorPage is the first page.
//        //  * nextKey == null -> anchorPage is the last page.
//        //  * both prevKey and nextKey are null -> anchorPage is the
//        //    initial page, so return null.
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//}
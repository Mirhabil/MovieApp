package com.example.letterboxd.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.log
import com.example.letterboxd.domain.model.GroupPopularListsThisMonthModel
import com.example.letterboxd.domain.repository.PopularMoviesRepo
import javax.inject.Inject

class GroupedPagingSource @Inject constructor(
    val backend: PopularMoviesRepo,
) : PagingSource<Int, GroupPopularListsThisMonthModel>() {
    override suspend fun load(
        params: PagingSource.LoadParams<Int>
    ): PagingSource.LoadResult<Int, GroupPopularListsThisMonthModel> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = backend.getGroupPopularListsThisMonth(nextPageNumber)
            Log.e("Feridddd", nextPageNumber.toString())
            Log.e("Feridddd", response.map { it.movie1.movieId1.toString() }.toString())

            return PagingSource.LoadResult.Page(
                data = response,
                prevKey = null, // Only paging forward.
                nextKey = nextPageNumber+1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
            // Handle errors in this block and return LoadResult.Error for
            // expected errors (such as a network failure).
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GroupPopularListsThisMonthModel>): Int? {
        // Try to find the page key of the closest page to anchorPosition from
        // either the prevKey or the nextKey; you need to handle nullability
        // here.
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey are null -> anchorPage is the
        //    initial page, so return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
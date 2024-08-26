package com.example.letterboxd.domain.repository

import androidx.paging.PagingSource
import com.example.letterboxd.data.remote.model.PeopleListsResponse.ResultX
import com.example.letterboxd.data.remote.model.PersonIdResponse.PersonIdResponse
import com.example.letterboxd.data.remote.model.SearchResponse.SearchResponse
import com.example.letterboxd.domain.model.AllReviewsModel
import com.example.letterboxd.domain.model.CastsAndCrewsModel
import com.example.letterboxd.domain.model.GroupPopularListsThisMonthModel
import com.example.letterboxd.domain.model.ImagesModel
import com.example.letterboxd.domain.model.KyransRecentWatchedModel
import com.example.letterboxd.domain.model.PersonIdModel
import com.example.letterboxd.domain.model.PopularFilmsThisMonthModel
import com.example.letterboxd.domain.model.PopularListsThisMonthModel
import com.example.letterboxd.domain.model.RecentFriendsReviewModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PopularMoviesRepo {


   // suspend fun getSearchResponse(moviename:String):List<PopularFilmsThisMonthModel>

    suspend fun getExplorePageFilms(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): List<PopularFilmsThisMonthModel>

    @GET("search/movie")
    suspend fun searchFilms(
        @Query("api_key") apiKey: String,
        @Query("query") movieName: String,
        @Query("page") page: Int
    ): List<PopularFilmsThisMonthModel>


    suspend fun getPopularMovies(page: Int): List<PopularFilmsThisMonthModel>

    //suspend fun getRecentFriendsReviewModel(): List<RecentFriendsReviewModel>

    suspend fun getPeopleLists(): List<RecentFriendsReviewModel>

    suspend fun getPopularListsThisMonth(page:Int):List<PopularListsThisMonthModel>

    suspend fun getGroupPopularListsThisMonth(page:Int):List<GroupPopularListsThisMonthModel>

    suspend fun getReviewsRecycler(@Path("id") movieID:Int?): List<AllReviewsModel>

    suspend fun getCasts(@Path("movie_id") movieID: Int?):List<CastsAndCrewsModel>

    suspend fun getCrews(@Path("movie_id") movieID: Int?):List<CastsAndCrewsModel>

    suspend fun getMoviePageBackgroundImage(@Path("movie_id") movieID: Int?):List<ImagesModel>

    suspend fun getNowPlayingResponse():List<KyransRecentWatchedModel>

    suspend fun getDetailsResponse(@Path("movie_id") movieID: Int?):PopularFilmsThisMonthModel?

    suspend fun getPersonIdResponse(@Path("person_id") personId:Int):PersonIdResponse

}
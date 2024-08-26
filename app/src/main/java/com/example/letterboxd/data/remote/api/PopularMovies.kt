package com.example.letterboxd.data.remote.api

import com.example.letterboxd.data.remote.model.CreditsResponse.CreditsResponse
import com.example.letterboxd.data.remote.model.DetailsResponse.DetailsResponse
import com.example.letterboxd.data.remote.model.ImagesResponse.ImagesResponse
import com.example.letterboxd.data.remote.model.NowPlayingResponse.NowPlayingResponse
import com.example.letterboxd.data.remote.model.PeopleListsResponse.PeopleListsResponse
import com.example.letterboxd.data.remote.model.PersonIdResponse.PersonIdResponse
import com.example.letterboxd.data.remote.model.PopularMoviesResponse.PopularMoviesResponse
import com.example.letterboxd.data.remote.model.ReviewsResponse.ReviewsResponse
import com.example.letterboxd.data.remote.model.SearchResponse.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PopularMovies {

//    @GET("search/movie")
//    suspend fun getSearch(@Query("query")moviname:String,@Query("api_key")apiKey:String="fa22bdcd3f461d2aec53c6b2b47e85e7"):SearchResponse

    @GET("discover/movie")
    suspend fun getExplorePageFilms(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): SearchResponse

    @GET("search/movie")
    suspend fun searchFilms(
        @Query("api_key") apiKey: String,
        @Query("query") movieName: String,
        @Query("page") page: Int
    ): SearchResponse

    @GET("movie/top_rated")
    suspend fun getPopularMovies(@Query("page")page:Int): PopularMoviesResponse
 
    @GET("person/popular")
    suspend fun getPeopleLists(): PeopleListsResponse


    @GET("movie/{id}/reviews")
    suspend fun getReviews(@Path("id") movieID:Int?):ReviewsResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(@Path("movie_id") movieID: Int?):CreditsResponse

    @GET("movie/{movie_id}/images")
    suspend fun getImages(@Path("movie_id") movieID:Int?):ImagesResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingApi():NowPlayingResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailsResponseApi(@Path("movie_id") movieID:Int?):DetailsResponse

    @GET("person/{person_id}")
    suspend fun getPersonData(@Path("person_id") personId:Int):PersonIdResponse



}
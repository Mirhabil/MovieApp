package com.example.letterboxd.data.remote.repository

import com.example.letterboxd.data.remote.api.PopularMovies
import com.example.letterboxd.data.remote.model.PersonIdResponse.PersonIdResponse
import com.example.letterboxd.domain.model.AllReviewsModel
import com.example.letterboxd.domain.model.CastsAndCrewsModel
import com.example.letterboxd.domain.model.GroupPopularListsThisMonthModel
import com.example.letterboxd.domain.model.ImagesModel
import com.example.letterboxd.domain.model.KyransRecentWatchedModel
import com.example.letterboxd.domain.model.PopularFilmsThisMonthModel
import com.example.letterboxd.domain.model.PopularListsThisMonthModel
import com.example.letterboxd.domain.model.RecentFriendsReviewModel
import com.example.letterboxd.domain.repository.PopularMoviesRepo
import javax.inject.Inject
import kotlin.random.Random

class PopularMoviesRepoImpl @Inject constructor(val apiPopularMovies:PopularMovies):PopularMoviesRepo {
    override suspend fun getExplorePageFilms(
        apiKey: String,
        page: Int
    ): List<PopularFilmsThisMonthModel> {

        return apiPopularMovies.getExplorePageFilms(apiKey,page).results.map{
            PopularFilmsThisMonthModel(


                image = it?.poster_path.orEmpty(),
                moviname = it.original_title,
                movieDescription = it.overview,
                movieYear = it.release_date.split("-").first(),
                movieID = it.id,
                backdropPath = it?.poster_path.orEmpty(),
                rating = it.vote_average



            )
        }

    }

    override suspend fun searchFilms(
        apiKey: String,
        movieName: String,
        page: Int
    ): List<PopularFilmsThisMonthModel> {

        return apiPopularMovies.searchFilms(apiKey, movieName, page).results.map {

            PopularFilmsThisMonthModel(


                image = it?.poster_path.orEmpty(),
                moviname = it.original_title,
                movieDescription = it.overview,
                movieYear = it.release_date.split("-").first(),
                movieID = it.id,
                backdropPath = it?.poster_path.orEmpty(),
                rating = it.vote_average



            )


        }







    }
//    override suspend fun getSearchResponse(moviename: String): List<PopularFilmsThisMonthModel> {
//        return apiPopularMovies.getSearch(moviename).results.map {
//
//            PopularFilmsThisMonthModel(
//
//                image = it?.poster_path.orEmpty(),
//                moviname = it.original_title,
//                movieDescription = it.overview,
//                movieYear = it.release_date.split("-").first(),
//                movieID = it.id,
//                backdropPath = it?.poster_path.orEmpty(),
//                rating = it.vote_average
//
//            )
//        }
//    }




    override suspend fun getPopularMovies(page: Int): List<PopularFilmsThisMonthModel> {
        return apiPopularMovies.getPopularMovies(2).results.map {
            PopularFilmsThisMonthModel(
                image = it.poster_path,
                moviname = it.original_title,
                movieDescription = it.overview,
                movieYear = it.release_date.split("-").first(),
                movieID = it.id,
                backdropPath = it.backdrop_path,
                rating = it.vote_average
            )
        }
    }



    override suspend fun getPeopleLists(): List<RecentFriendsReviewModel> {
        return apiPopularMovies.getPeopleLists().results.flatMap {ResultX->

            ResultX.known_for.map { knownFor ->
                RecentFriendsReviewModel(
                    profileImage = ResultX.profile_path ?: "",  // Use default value if profile_path is null
                    postImage = knownFor.poster_path ?: "",     // Use default value if poster_path is null
                    movieName = knownFor.original_title ?: "",   // Use default value if original_name is null
                    movieYear = knownFor.release_date?.substring(0, 4) ?: "",  // Extract year or use default
                    movieDescription = knownFor.overview ?: "" , // Use default value if overview is null
                    movieActor = ResultX.name,
                    rating = knownFor.vote_average,
                    movieId = knownFor.id

                )
            }.shuffled()

        }
    }

    /*
    override suspend fun getRecentFriendsReviewModel(): List<RecentFriendsReviewModel> {

        return apiPopularMovies.getPopularMovies().results.map {

            RecentFriendsReviewModel(

                profileImage = it.poster_path,
                postImage = it.poster_path,
                movieName = it.original_title,
                movieYear = it.release_date,
                movieDescription = it.overview
            )
        }
    }

     */

    override suspend fun getPopularListsThisMonth(page:Int): List<PopularListsThisMonthModel> {

        return apiPopularMovies.getPopularMovies(page).results.map {

            PopularListsThisMonthModel(

                classImage1 = it.poster_path,
                movieId1 = it.id,
                classImage2 = it.poster_path,
                movieId2 = it.id,
                classImage3 = it.poster_path,
                movieId3 = it.id,
                classImage4 = it.poster_path,
                movieId4 = it.id
            )
        }.shuffled()

    }

    override suspend fun getGroupPopularListsThisMonth(page:Int): List<GroupPopularListsThisMonthModel> {

        val popularLists=getPopularListsThisMonth(page)

        return getGroupedPopularListsThisMonth(popularLists).shuffled()

    }

    override suspend fun getReviewsRecycler(movieID: Int?): List<AllReviewsModel> {

        return apiPopularMovies.getReviews(movieID).results.map {
            AllReviewsModel(

                profileImage = it.author_details.avatar_path ?:"",
                personName = it.author_details.name,
                review =it.content,
                rating = getRandomFloatInRange(1f,5f).toDouble()
            )
        }.reversed()



    }

    override suspend fun getCasts(movieID: Int?): List<CastsAndCrewsModel> {
        return apiPopularMovies.getCredits(movieID).cast.map {

            CastsAndCrewsModel(

                it.profile_path?:"" ,
                it.id
            )
        }
    }

    override suspend fun getCrews(movieID: Int?): List<CastsAndCrewsModel> {
        return apiPopularMovies.getCredits(movieID).crew.map {

            CastsAndCrewsModel(

                it.profile_path?:"",
                it.id
            )
        }
    }

    override suspend fun getMoviePageBackgroundImage(movieID: Int?): List<ImagesModel> {
        return apiPopularMovies.getImages(movieID).posters.map {

            ImagesModel(

                it.file_path?:""
            )
        }
    }

    override suspend fun getNowPlayingResponse(): List<KyransRecentWatchedModel> {

        return apiPopularMovies.getNowPlayingApi().results.map {

            KyransRecentWatchedModel(
                it.id,
                it.poster_path,
                it.vote_average
            )
        }.shuffled()
    }

    override suspend fun getDetailsResponse(movieID: Int?): PopularFilmsThisMonthModel? {

        val a=apiPopularMovies.getDetailsResponseApi(movieID)
        return a?.let {film->

                PopularFilmsThisMonthModel(
                    image = film.poster_path,
                    moviname = film.original_title,
                    movieDescription = film.overview,
                    movieYear = film.release_date,
                    movieID=film.id,
                    backdropPath = film.backdrop_path,
                    rating = film.vote_average


            )

        }



    }

    override suspend fun getPersonIdResponse(personId: Int): PersonIdResponse {
        return apiPopularMovies.getPersonData(personId)
    }


    fun getGroupedPopularListsThisMonth(data:List<PopularListsThisMonthModel>):List<GroupPopularListsThisMonthModel>{

        val groupCount=data.size/4
        val groupedData= mutableListOf<List<PopularListsThisMonthModel>>()

        repeat(groupCount){

            val start=it*4
            val end=start+4

            val sublist=data.subList(start,end)
            groupedData.add(sublist)
        }

        val finalResult=groupedData.map {group->
            val movieIds = group.flatMap { listOf(it.movieId1, it.movieId2, it.movieId3, it.movieId4) }
            GroupPopularListsThisMonthModel(group[0],group[1],group[2],group[3], movieIds)
        }
        return finalResult


    }


    fun getRandomFloatInRange(min: Float, max: Float): Float {
        require(min < max) { "Max must be greater than min" }
        return Random.nextFloat() * (max - min) + min
    }
}


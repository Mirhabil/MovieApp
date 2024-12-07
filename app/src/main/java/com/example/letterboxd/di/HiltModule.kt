package com.example.letterboxd.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.letterboxd.data.local.ImageDatabase.ImageDatabase
import com.example.letterboxd.data.local.ReviewsDatabase.ReviewsDatabase
import com.example.letterboxd.data.local.WatchlistDatabase.WatchlistDatabase
import com.example.letterboxd.data.remote.api.PopularMovies
import com.example.letterboxd.data.remote.repository.FireStoreRepoImpl
import com.example.letterboxd.data.remote.repository.PopularMoviesRepoImpl
import com.example.letterboxd.domain.repository.FireStoreRepo
import com.example.letterboxd.domain.repository.PopularMoviesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltModule{


    @Named("UserProfileImage")
    @Provides
    fun provideSharedPreferenceUserProfile(@ApplicationContext context: android.content.Context) : SharedPreferences {
        return context.getSharedPreferences("userProfileImage", android.content.Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun getImageDataBase(context:Context): ImageDatabase {
        return Room.databaseBuilder(

            context, ImageDatabase::class.java, "image_database"
        ).build()
    }



    @Singleton
    @Provides
    fun getWatchListDatabase(context:Context): WatchlistDatabase {
        return Room.databaseBuilder(

            context, WatchlistDatabase::class.java, "whatchlist_database"
        ).build()
    }

    @Singleton
    @Provides
    fun getReviewsDatabase(context:Context): ReviewsDatabase {
        return Room.databaseBuilder(

            context, ReviewsDatabase::class.java, "reviews_database"
        ).build()
    }


    @Singleton
    @Provides
    fun getFireStoreRepo():FireStoreRepo{

        return FireStoreRepoImpl()
    }

    @Singleton
    @Provides
    fun getClient():Retrofit{
        val client = OkHttpClient.Builder().addInterceptor(APIKeyInterceptor()).build()
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    class APIKeyInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val currentUrl = chain.request().url()
            val newUrl = currentUrl.newBuilder().addQueryParameter("api_key", "fa22bdcd3f461d2aec53c6b2b47e85e7").build()
            val currentRequest = chain.request().newBuilder()
            val newRequest = currentRequest.url(newUrl).build()
            return chain.proceed(newRequest)
        }
    }


    @Singleton
    @Provides
    fun getApi(client:Retrofit):PopularMovies{

        return client.create(PopularMovies::class.java)
    }

    @Singleton
    @Provides
    fun getPopularMoviesRepo(apiPopularMovies:PopularMovies):PopularMoviesRepo{

        return PopularMoviesRepoImpl(apiPopularMovies)
    }



}
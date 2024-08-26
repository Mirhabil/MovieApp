package com.example.letterboxd.presentation.Fragments.MoviePageFragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.letterboxd.R
import com.example.letterboxd.data.local.ReviewsDatabase.ReviewsDatabase
import com.example.letterboxd.data.local.WatchlistDatabase.WatchlistDatabase
import com.example.letterboxd.data.local.WatchlistDatabase.WatchlistEntity
import com.example.letterboxd.data.remote.api.PopularMovies
import com.example.letterboxd.data.remote.toAllReviewsModel
import com.example.letterboxd.databinding.FragmentMoviepageBinding
import com.example.letterboxd.domain.model.AllReviewsModel
import com.example.letterboxd.domain.model.PopularFilmsThisMonthModel
import com.example.letterboxd.presentation.Adapters.CastsAndCrewsAdapter
import com.example.letterboxd.domain.repository.PopularMoviesRepo
import com.example.letterboxd.presentation.Adapters.AllReviewsAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.round
import kotlin.math.roundToLong

@AndroidEntryPoint
class MoviePageFragment :Fragment() {

    var exist=false


    @Inject
    lateinit var reviewsDatabase:ReviewsDatabase
    @Inject
    lateinit var watchListDatabase: WatchlistDatabase

    @Inject
    lateinit var api:PopularMovies

    //var a:PopularFilmsThisMonthModel?=null
    lateinit var a:PopularFilmsThisMonthModel

    val viewModel:MoviePageViewModel by viewModels()

    val adapterCastsAndCrews= CastsAndCrewsAdapter(
        onClick = {findNavController().navigate(MoviePageFragmentDirections.actionMoviePageFragmentToCastsBottomSheetFragment(it))}
    )

    @Inject
    lateinit var popularMoviesRepo:PopularMoviesRepo

    val adapter=AllReviewsAdapter()


    val args by navArgs<MoviePageFragmentArgs>()

    private var _binding:FragmentMoviepageBinding?=null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentMoviepageBinding.inflate(layoutInflater)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

            exist=checkDatabaseExistence(args.movieId)

            if(exist){

                binding.buttonAddToWatchlist.setBackgroundColor(Color.GREEN)
                binding.buttonAddToWatchlist.text="Aded Movie"
                binding.buttonAddToWatchlist.setTextColor(Color.BLACK)
            }





        }

        binding.buttonAddToWatchlist.setOnClickListener {

            if (exist){

                lifecycleScope.launch {

                    withContext(Dispatchers.IO){

                        watchListDatabase.getWatchListDao().deletePosterById(args.movieId)
                        exist=false
                    }
                }

                Snackbar.make(view, "Removed from Watchlist", Snackbar.LENGTH_SHORT)
                    .show()

                binding.buttonAddToWatchlist.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.important_color))

                binding.buttonAddToWatchlist.text="Add to Watchlist"

                binding.buttonAddToWatchlist.setTextColor(Color.WHITE)





            }

            else{

                lifecycleScope.launch {

                    withContext(Dispatchers.IO){


                        watchListDatabase.getWatchListDao().insertPoster(WatchlistEntity(args.movieId,a.image,a.rating,a.moviname,a.movieYear))
                        exist=true
                    }
                }

                Snackbar.make(view, "Added to Watchlist", Snackbar.LENGTH_SHORT)
                    .show()

                binding.buttonAddToWatchlist.setBackgroundColor(Color.GREEN)
                binding.buttonAddToWatchlist.text="Aded Movie"
                binding.buttonAddToWatchlist.setTextColor(Color.BLACK)




            }
        }





        binding.rvAllReviews.adapter=adapter
        binding.rvCastsAndCrews.adapter=adapterCastsAndCrews

        //binding.imageView10.background=ContextCompat.getDrawable(requireContext(), R.drawable.rounded_corner_bottom_right)
//        Glide.with(binding.imageView11).load("https://image.tmdb.org/t/p/w500"+ args.popularFilmsThisMonthParcalableData.image).into(binding.imageView11)
//        binding.textViewMovieNameMP.text=args.popularFilmsThisMonthParcalableData.moviname
//        binding.textViewMovieDescriptionMP.text=args.popularFilmsThisMonthParcalableData.movieDescription
//        binding.textViewMovieYearMP.text=args.popularFilmsThisMonthParcalableData.movieYear
//


//        lifecycleScope.launch {
//
//            val movieItem=api.getDetailsResponseApi(args.movieId)
//            Glide.with(binding.imageView11).load("https://image.tmdb.org/t/p/w500"+movieItem.poster_path).into(binding.imageView11)
//        }


        lifecycleScope.launch {



            viewModel.getDetailsResponseMovieId(args.movieId)
            viewModel.detailsFlow.filterNotNull().collectLatest {

                a=it



                Glide.with(binding.imageView11).load("https://image.tmdb.org/t/p/w500"+ it.image).into(binding.imageView11)
                binding.textViewMovieNameMP.text=it.moviname
                binding.textViewMovieDescriptionMP.text=it.movieDescription
                binding.textViewMovieYearMP.text=it.movieYear.split("-").first()
                binding.imageView19.setImageWithGlide("https://image.tmdb.org/t/p/w500"+ it.backdropPath)
                val floatValue= ((it.rating)/2).round(1)
                binding.textView41.text= floatValue.toString()
                binding.ratingBar3.rating=floatValue.toFloat()



            }
        }


        lifecycleScope.launch {

            val reviews= mutableListOf<AllReviewsModel>()
            (args.movieId-10..args.movieId+10).map {

                launch {

                    runCatching {
                        val data=popularMoviesRepo.getReviewsRecycler(it)
                        reviews.addAll(data)
                    }


                }






            }.joinAll()

            val databaseReviews=reviewsDatabase.getReviewsDao().getALLPosters().map {

                it.toAllReviewsModel()
            }



            adapter.getAdapterList(reviews+databaseReviews)




        }














//        lifecycleScope.launch {
//
//            val reviews= mutableListOf<AllReviewsModel>()
//            (args.movieId-15..args.movieId+10).forEach {
//
//
//                runCatching {
//                    val data=popularMoviesRepo.getReviewsRecycler(it)
//                    reviews.addAll(data)
//                }
//
//
//
//            }
//
//
//
//            adapter.getAdapterList(reviews)
//
//
//
//
//        }

        viewModel.getMovieId(args.movieId)




        lifecycleScope.launch {

            viewModel.castsResponseFlow.collectLatest{

                adapterCastsAndCrews.getAdapterList(it)


            }
        }

        binding.tabLayout.addOnTabSelectedListener(

            object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {

                    if(tab?.position==0){

                        viewModel.getMovieId(args.movieId)

                        lifecycleScope.launch {

                            viewModel.castsResponseFlow.collectLatest{

                                adapterCastsAndCrews.getAdapterList(it)
                            }
                        }


                    }
                    else{

                        viewModel.getCrewsMovieId(args.movieId)

                        lifecycleScope.launch {

                            viewModel.crewsResponseFlow.collectLatest{

                                adapterCastsAndCrews.getAdapterList(it)
                            }
                        }


                    }


                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }


            }
        )


//        binding.buttonCasts.setOnClickListener {
//
//
//
//
//
//            viewModel.getMovieId(args.popularFilmsThisMonthParcalableData.movieID)
//
//            lifecycleScope.launch {
//
//                viewModel.castsResponseFlow.collectLatest{
//
//                    adapterCastsAndCrews.getAdapterList(it)
//                }
//            }
//
//
//        }
//
//        binding.buttonCrews.setOnClickListener {
//
//
//            viewModel.getCrewsMovieId(args.popularFilmsThisMonthParcalableData.movieID)
//
//            lifecycleScope.launch {
//
//                viewModel.crewsResponseFlow.collectLatest{
//
//                    adapterCastsAndCrews.getAdapterList(it)
//                }
//            }
//
//
//        }

        viewModel.getImagesMovieId(args.movieId)

        viewModel.getMovieId(args.movieId)

        lifecycleScope.launch {

            viewModel.imagesResponseFlow.filter { it.isNotEmpty() }.collectLatest {


               // binding.imageView19.setImageWithGlide("https://image.tmdb.org/t/p/w200"+ it.first().image)
                Log.e("Firstttt",it.first().toString())

            }
        }













        binding.buttonRateOrReview.setOnClickListener {

            findNavController().navigate(MoviePageFragmentDirections.actionMoviePageFragmentToReviewPageFragment(
                a

            ))
        }
//
//        binding.buttonAddToWatchlist.setOnClickListener {
//
//
//
//            lifecycleScope.launch {
//
//                withContext(Dispatchers.IO){
//
//                    watchListDatabase.getWatchListDao().insertPoster(WatchlistEntity(args.movieId,a.image))
//
//
//                }
//
//
//
//
//            }
//
//
//        }

//        binding.textView35.setOnClickListener {
//
//            findNavController().navigate(R.id.action_moviePageFragment_to_watchListFragment)
//        }




    }

   suspend fun checkDatabaseExistence(movieId:Int):Boolean{

       val exist=watchListDatabase.getWatchListDao().exists(movieId)>0

        return exist



    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}


fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}
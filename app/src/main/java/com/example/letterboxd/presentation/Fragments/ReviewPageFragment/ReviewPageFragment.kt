package com.example.letterboxd.presentation.Fragments.ReviewPageFragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.letterboxd.R
import com.example.letterboxd.data.local.ImageDatabase.ImageDatabase
import com.example.letterboxd.data.local.ImageDatabase.PosterDAO
import com.example.letterboxd.data.local.ImageDatabase.PosterEntity
import com.example.letterboxd.data.local.ReviewsDatabase.ReviewsDatabase
import com.example.letterboxd.data.local.ReviewsDatabase.ReviewsEntity
import com.example.letterboxd.databinding.FragmentReviewpageBinding
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Named
import kotlin.random.Random

@AndroidEntryPoint
class ReviewPageFragment : Fragment() {




    @Inject
    @Named("UserProfileImage")
    lateinit var shPref:SharedPreferences

    @Inject
    lateinit var imageDatabase: ImageDatabase

    @Inject
    lateinit var reviewsDatabase:ReviewsDatabase

    val args by navArgs<ReviewPageFragmentArgs>()
    val viewModel:ReviewPageViewModel by viewModels()

    private var _binding: FragmentReviewpageBinding? = null
    private val binding get() = _binding!!
    private lateinit var posterDao: PosterDAO
    private var exists=false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewpageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.imageView29.setOnClickListener {

            findNavController().popBackStack()
        }



//        val imageDatabase = Room.databaseBuilder(
//
//            requireContext(), ImageDatabase::class.java, "image_database"
//        ).build()

        posterDao = imageDatabase.getPosterDAO()
        lifecycleScope.launch {

            exists=checkItemExists(args.popularFilmsThisMonthModelParcalableData.movieID)

            if (exists) {
                // Item exists
                binding.imageViewLikeRV.setImageResource(R.drawable.vector_like)
            } else {
                // Item does not exist
                binding.imageViewLikeRV.setImageResource(R.drawable.vectorlike)
            }
        }




        val todayDate = Date()
        val dateFormatted = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(todayDate)
        binding.buttonDatePicker.text = dateFormatted

        binding.buttonDatePicker.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Watching date")
                .build()

            datePicker.show(parentFragmentManager, "datePicker")

            datePicker.addOnPositiveButtonClickListener { selection ->
                val selectedDate =
                    SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(selection))
                binding.buttonDatePicker.text = selectedDate
            }

        }
        val scope = CoroutineScope(Dispatchers.IO)

        lifecycleScope.launch {

            viewModel.getPopularFilms(args.popularFilmsThisMonthModelParcalableData.movieID)

            viewModel.responseFlow.collectLatest {

                Glide.with(binding.imageView21)
                    .load("https://image.tmdb.org/t/p/w500" + it?.image)
                    .into(binding.imageView21)
                binding.textViewMovieName.text = it?.moviname
                binding.textViewMovieYearRP.text = it?.movieYear?.split("-")?.first()




            }


        }



//        Glide.with(binding.imageView21)
//            .load("https://image.tmdb.org/t/p/w500" + args.reviewpageparcelabledata.image)
//            .into(binding.imageView21)
//        binding.textViewMovieName.text = args.reviewpageparcelabledata.moviname
//        binding.textViewMovieYearRP.text = args.reviewpageparcelabledata.movieYear

        binding.imageViewLikeRV.setOnClickListener {

            if (exists){
                lifecycleScope.launch {

                    withContext(Dispatchers.IO) {
                        posterDao.deletePosterById(args.popularFilmsThisMonthModelParcalableData.movieID)
                    }

                    binding.imageViewLikeRV.setImageResource(R.drawable.vectorlike)


                }

            }


            else{
                lifecycleScope.launch {



                    withContext(Dispatchers.IO) {
                        posterDao.insertPoster(PosterEntity(id=args.popularFilmsThisMonthModelParcalableData.movieID,args.popularFilmsThisMonthModelParcalableData.image))
                    }

                    binding.imageViewLikeRV.setImageResource(R.drawable.vector_like)


                }
            }

        }

//        binding.imageViewLikeRV.setOnClickListener {
//
//            scope.launch {
//
//                posterDao.insertPoster(PosterEntity(id=args.reviewpageparcelabledata.movieID,imageURL = "https://image.tmdb.org/t/p/w500" +args.reviewpageparcelabledata.image))
//
//            }
//
//
//        }

        binding.buttonPublish.setOnClickListener {

            val review=binding.editTextPublish.text.toString()

            lifecycleScope.launch {

                reviewsDatabase.getReviewsDao().insertPoster(ReviewsEntity(movieId = args.popularFilmsThisMonthModelParcalableData.movieID, imageUrl = shPref.getString("Salam",null)!!, review = review, movieName = args.popularFilmsThisMonthModelParcalableData.moviname, movieYear = args.popularFilmsThisMonthModelParcalableData.movieYear, rating =getRandomFloatInRange(1f,5f).toDouble() ))

            }



        }


    }

    fun getRandomFloatInRange(min: Float, max: Float): Float {
        require(min < max) { "Max must be greater than min" }
        return Random.nextFloat() * (max - min) + min
    }

    suspend fun checkItemExists(itemId: Int):Boolean {

        val exists = posterDao.exists(itemId) > 0
//        withContext(Dispatchers.Main) {
//            if (exists) {
//                // Item exists
//                binding.imageViewLikeRV.setImageResource(R.drawable.vector_like)
//            } else {
//                // Item does not exist
//                binding.imageViewLikeRV.setImageResource(R.drawable.vectorlike)
//            }
//        }
        return exists

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
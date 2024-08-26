package com.example.letterboxd.presentation.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.letterboxd.R
import com.example.letterboxd.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment(){

    private var _binding:FragmentBottomSheetBinding?=null
    private val binding get() = _binding!!

    val args by navArgs<BottomSheetFragmentArgs>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        Log.e("ArgsResponseeee",args.groupListsTHisMonthParcalableData.toString())
        val movieData = args.groupListsTHisMonthParcalableData

//// List of image views and corresponding movie IDs
//        val imageViewMoviePairs = listOf(
//            Pair(binding.imageView10, movieData.movie1.movieId1),
//            Pair(binding.imageView23, movieData.movie2.movieId2),
//            Pair(binding.imageView24, movieData.movie3.movieId3),
//            Pair(binding.imageView25, movieData.movie4.movieId4)
//        )
//
//// Load images using Glide and set click listeners
//        imageViewMoviePairs.forEach { (imageView, movieId) ->
//            Glide.with(imageView)
//                .load("https://image.tmdb.org/t/p/w500${movieData.classImage1}")
//                .error(R.drawable.userprofilefoto)
//                .into(imageView)
//
//            imageView.setOnClickListener {
//                findNavController().navigate(
//                    BottomSheetFragmentDirections.actionBottomSheetFragmentToMoviePageFragment(movieId)
//                )
//            }
//        }




//
        Glide.with(binding.imageView10).load("https://image.tmdb.org/t/p/w500"+args.groupListsTHisMonthParcalableData.movie1.classImage1).error(R.drawable.userprofilefoto).into(binding.imageView10)
        Glide.with(binding.imageView23).load("https://image.tmdb.org/t/p/w500"+args.groupListsTHisMonthParcalableData.movie2.classImage2).into(binding.imageView23)
        Glide.with(binding.imageView24).load("https://image.tmdb.org/t/p/w500"+args.groupListsTHisMonthParcalableData.movie3.classImage3).into(binding.imageView24)
        Glide.with(binding.imageView25).load("https://image.tmdb.org/t/p/w500"+args.groupListsTHisMonthParcalableData.movie4.classImage4).into(binding.imageView25)

        Log.e("ArgsResponseeee",args.groupListsTHisMonthParcalableData.toString())


        binding.imageView10.setOnClickListener {

            findNavController().navigate(BottomSheetFragmentDirections.actionBottomSheetFragmentToMoviePageFragment(args.groupListsTHisMonthParcalableData.movie1.movieId1))
        }
        binding.imageView23.setOnClickListener {

            findNavController().navigate(BottomSheetFragmentDirections.actionBottomSheetFragmentToMoviePageFragment(args.groupListsTHisMonthParcalableData.movie2.movieId2))
        }

        binding.imageView24.setOnClickListener {

            findNavController().navigate(BottomSheetFragmentDirections.actionBottomSheetFragmentToMoviePageFragment(args.groupListsTHisMonthParcalableData.movie3.movieId3))
        }

        binding.imageView25.setOnClickListener {

            findNavController().navigate(BottomSheetFragmentDirections.actionBottomSheetFragmentToMoviePageFragment(args.groupListsTHisMonthParcalableData.movie4.movieId4))
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}
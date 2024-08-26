package com.example.letterboxd.presentation.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.letterboxd.databinding.LargereviewdesignBinding

class LargeReviewFragment:Fragment() {

    val args:LargeReviewFragmentArgs by navArgs()

    private var _binding: LargereviewdesignBinding?=null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Glide.with(binding.imageViewPpL).load("https://image.tmdb.org/t/p/w500"+ args.largeReviewParcelableData.profileImage).into(binding.imageViewPpL)
        Glide.with(binding.imageView22).load("https://image.tmdb.org/t/p/w500"+ args.largeReviewParcelableData.postImage).into(binding.imageView22)
        binding.textView46UserName.text=args.largeReviewParcelableData .movieActor
        binding.textView48MovieName.text=args.largeReviewParcelableData.movieName
        binding.textView49MovieYear.text=args.largeReviewParcelableData.movieYear
        binding.textView50MovieDescription.text=args.largeReviewParcelableData.movieDescription
        binding.ratingBar7.rating=args.largeReviewParcelableData.rating.toFloat()

        binding.button.setOnClickListener {

            findNavController().navigate(LargeReviewFragmentDirections.actionLargeReviewFragmentToMoviePageFragment(args.largeReviewParcelableData.movieId))
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= LargereviewdesignBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}
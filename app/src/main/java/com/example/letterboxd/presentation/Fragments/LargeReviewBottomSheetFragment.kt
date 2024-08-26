package com.example.letterboxd.presentation.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.letterboxd.databinding.LargereviewdesignBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

//class LargeReviewBottomSheetFragment:BottomSheetDialogFragment() {
//
//    val args:LargeReviewBottomSheetFragmentArgs by navArgs()
//
//    private var _binding:LargereviewdesignBinding?=null
//    private val binding get() = _binding!!
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//        Glide.with(binding.imageViewPpL).load("https://image.tmdb.org/t/p/w500"+ args.largereviewParcelableData.profileImage).into(binding.imageViewPpL)
//        Glide.with(binding.imageView22).load("https://image.tmdb.org/t/p/w500"+ args.largereviewParcelableData.postImage).into(binding.imageView22)
//        binding.textView46UserName.text=args.largereviewParcelableData.movieActor
//        binding.textView48MovieName.text=args.largereviewParcelableData.movieName
//        binding.textView49MovieYear.text=args.largereviewParcelableData.movieYear
//        binding.textView50MovieDescription.text=args.largereviewParcelableData.movieDescription
//        binding.ratingBar7.rating=args.largereviewParcelableData.rating.toFloat()
//
//
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding=LargereviewdesignBinding.inflate(layoutInflater)
//        return binding.root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding=null
//    }
//}
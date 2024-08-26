package com.example.letterboxd.presentation.Fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.letterboxd.R
import com.example.letterboxd.databinding.FragmentHomepageBinding
import com.example.letterboxd.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private val splashTimeOut: Long = 3000 // 3 seconds

    private var _binding: FragmentSplashBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
        }, splashTimeOut)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}
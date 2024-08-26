package com.example.letterboxd.presentation.Fragments.OnBoardingFragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.letterboxd.databinding.FragmentOnboardingBinding
import com.example.letterboxd.presentation.Activities.MainActivity.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment: Fragment() {

    private var _binding:FragmentOnboardingBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentOnboardingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shPref=requireContext().getSharedPreferences("userstate", Context.MODE_PRIVATE)
        if(shPref.getBoolean("activation", true)){

            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

        binding.buttonGetStarted.setOnClickListener {

            findNavController().navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToLoginFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}
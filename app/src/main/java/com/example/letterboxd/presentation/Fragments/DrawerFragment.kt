package com.example.letterboxd.presentation.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.letterboxd.databinding.HomepagedrawerdesignBinding

class DrawerFragment:Fragment() {

    private var _binding:HomepagedrawerdesignBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=HomepagedrawerdesignBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonWatchList.setOnClickListener {

            findNavController().navigate(DrawerFragmentDirections.actionDrawerFragmentToWatchListFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}
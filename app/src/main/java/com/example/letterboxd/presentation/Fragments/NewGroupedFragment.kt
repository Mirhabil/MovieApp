package com.example.letterboxd.presentation.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.letterboxd.R
import com.example.letterboxd.databinding.NewGroupNewfragmentBinding
import com.example.letterboxd.presentation.Adapters.NewGroupedAdapter
import com.example.letterboxd.presentation.Adapters.NewGroupedViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewGroupedFragment : Fragment() {

    val viewModel:NewGroupedViewModel by viewModels()


    val adapter=NewGroupedAdapter()

    lateinit var binding:NewGroupNewfragmentBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=NewGroupNewfragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView=binding.groupedRv
        recyclerView.adapter=adapter

        lifecycleScope.launch {

            viewModel.flow.collectLatest {

                adapter.submitData(it)
                Log.e("Feridddd", it.toString())


            }


        }

    }


}
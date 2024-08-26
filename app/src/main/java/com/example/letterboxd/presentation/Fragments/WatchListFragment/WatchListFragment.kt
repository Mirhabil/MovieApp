package com.example.letterboxd.presentation.Fragments.WatchListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.letterboxd.data.local.WatchlistDatabase.WatchlistDatabase
import com.example.letterboxd.databinding.FragmentWatchlistBinding
import com.example.letterboxd.presentation.Adapters.WatchListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class WatchListFragment:Fragment() {


    @Inject
    lateinit var watchListDatabase: WatchlistDatabase




    private var _binding:FragmentWatchlistBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentWatchlistBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val adapter=WatchListAdapter(
            onClick = {findNavController().navigate(WatchListFragmentDirections.actionWatchListFragmentToMoviePageFragment(it))}
        )
        binding.watchListRV.adapter=adapter

        lifecycleScope.launch {

            withContext(Dispatchers.IO){

                val a=watchListDatabase.getWatchListDao().getALLPosters()

                requireActivity().runOnUiThread {
                    adapter.getAdapterList(a)

                }



            }
        }









    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}
package com.example.letterboxd.presentation.Fragments.ExplorePageFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letterboxd.R
import com.example.letterboxd.databinding.FragmentExplorePageBinding
import com.example.letterboxd.domain.model.PopularFilmsThisMonthModel
import com.example.letterboxd.presentation.Adapters.ExplorePageAdapter
import com.example.letterboxd.presentation.Adapters.SearchAndExploreAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.logging.Handler

@AndroidEntryPoint
class ExplorePageFragment : Fragment() {



    val viewModel:ExplorePageViewModelNew by viewModels()
    private lateinit var adapter: SearchAndExploreAdapter
//    val adapter=ExplorePageAdapter(
//
//
//
//        onClick = {findNavController().navigate(ExplorePageFragmentDirections.actionExplorePageFragmentToMoviePageFragment(it))}
//    )



    private var _binding:FragmentExplorePageBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentExplorePageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter=SearchAndExploreAdapter {

            findNavController().navigate(ExplorePageFragmentDirections.actionExplorePageFragmentToMoviePageFragment(it.id))


            //onClick = {findNavController().navigate(ExplorePageFragmentDirections.actionExplorePageFragmentToMoviePageFragment(it))}
        }

        binding.explorePageRV.adapter=adapter

//        binding.button9.setOnClickListener {
//
//            findNavController().navigate(R.id.action_explorePageFragment_to_searchFragment)
//        }

        lifecycleScope.launch {
            viewModel.pagedData.collectLatest {
                adapter.submitData(it)
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Update the search query in ViewModel

                viewModel.setSearchQuery(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Optionally update search query as text changes
                viewModel.setSearchQuery(newText)
                return true
            }
        })




//        lifecycleScope.launch {
//            viewModel.responseFlow.collectLatest { newData ->
//                adapter.addItems(newData)
//            }
//        }

//        binding.explorePageRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
//                val visibleItemCount = layoutManager.childCount
//                val totalItemCount = layoutManager.itemCount
//                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
//
//                if (!viewModel.isLoading.value && !viewModel.isLastPage.value) {
//                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
//                        && firstVisibleItemPosition >= 0
//                    ) {
//                        viewModel.fetchNextPage()
//                    }
//                }
//            }
//        })

//        lifecycleScope.launch {
//
//            viewModel.responseFlow.collectLatest {
//
//
//                adapter.getAdapterList(it)
//
//
//            }
//        }







    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}
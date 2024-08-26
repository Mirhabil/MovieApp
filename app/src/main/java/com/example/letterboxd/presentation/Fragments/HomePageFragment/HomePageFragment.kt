package com.example.letterboxd.presentation.Fragments.HomePageFragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.letterboxd.R
import com.example.letterboxd.databinding.FragmentHomepageBinding
import com.example.letterboxd.presentation.Activities.MainActivity.MainActivity
import com.example.letterboxd.presentation.Activities.OnBoardingActivity.OnBoardingActivity
import com.example.letterboxd.presentation.Adapters.PopularFilmsThisMonthAdapter
import com.example.letterboxd.presentation.Adapters.PopularListsThisMonthAdapter
import com.example.letterboxd.presentation.Adapters.RecentFriendsReviewAdapter
import com.example.letterboxd.presentation.Fragments.MoviePageFragment.round
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class HomePageFragment:Fragment() {


    //private lateinit var ImageSharedPref:SharedPreferences

    //lateinit var galleryLauncher: ActivityResultLauncher<String>


    @Inject
    @Named("UserProfileImage")
    lateinit var shpref:SharedPreferences











    val viewModel:HomePageViewModel by viewModels()

    val popularFilmsThisMonthAdapter=PopularFilmsThisMonthAdapter(
        onClick = {findNavController().navigate(HomePageFragmentDirections.actionHomePageFragmentToMoviePageFragment(it))}
    )
    val recentFriendsReviewAdapter=RecentFriendsReviewAdapter(

        onClick = {findNavController().navigate(HomePageFragmentDirections.actionHomePageFragmentToLargeReviewFragment(it))}
    )
    val popularListsThisMonthAdapter=PopularListsThisMonthAdapter(

        onClick = {findNavController().navigate(HomePageFragmentDirections.actionHomePageFragmentToBottomSheetFragment(it)).also {
        }}
    )

    private var _binding: FragmentHomepageBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {galleryUri->
//            try{
//                saveImageSahredPref(galleryUri)
//                binding.imageViewProfileHP.setImageURI(galleryUri)
//            }catch(e:Exception){
//                e.printStackTrace()
//            }
//
//        }

        _binding= FragmentHomepageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shPref=requireContext().getSharedPreferences("userstate", Context.MODE_PRIVATE)

        //binding.textView4.text=shPref.getString("username",null)

        binding.button8.setOnClickListener {

            startActivity(Intent(requireContext(), OnBoardingActivity::class.java))
            shPref.edit().putBoolean("activation",false).apply()

        }

        val sharedPref = requireContext().getSharedPreferences("userloggedin", Context.MODE_PRIVATE)

        binding.textView30Profile.text=sharedPref.getString("username",null)
        binding.textView4.text=sharedPref.getString("username",null)


        val uriProfile = shpref.getString("Salam", null)
        uriProfile?.let {
            Glide.with(binding.imageView20)
                .load(Uri.parse(uriProfile))
                .error(R.drawable.ic_launcher_background)
                .into(binding.imageView20)
        } ?: Log.d("ProfileFragment", "No URI found in SharedPreferences")



        val uriString = shpref.getString("Salam", null)
        uriString?.let {
            Glide.with(binding.imageViewProfileHP)
                .load(Uri.parse(uriString))
                .error(R.drawable.ic_launcher_background)
                .into(binding.imageViewProfileHP)
        } ?: Log.d("ProfileFragment", "No URI found in SharedPreferences")





        binding.buttonHome.setOnClickListener {
            findNavController().navigate(HomePageFragmentDirections.actionHomePageFragmentSelf())
        }

        binding.buttonWatchList.setOnClickListener {

            findNavController().navigate(HomePageFragmentDirections.actionHomePageFragmentToWatchListFragment())
        }

        binding.buttonFilms.setOnClickListener {

            findNavController().navigate(HomePageFragmentDirections.actionHomePageFragmentToExplorePageFragment())
        }



//        ImageSharedPref=requireContext().getSharedPreferences("mySharePref", Context.MODE_PRIVATE)



//        binding.imageViewProfileHP.setOnClickListener {
//
//            openGalleryForImage()
//        }

//        binding.imageViewProfileHP.setOnClickListener {
//            galleryLauncher.launch("image/*")
//        }






        binding.imageView17.setOnClickListener {

            binding.mydrawer.openDrawer(Gravity.LEFT,true)
            //findNavController().navigate(R.id.bottomSheetFragment)
        }


        binding.rvPopularFilmsThisMonth.adapter=popularFilmsThisMonthAdapter
        binding.rvRecentFriendsReview.adapter=recentFriendsReviewAdapter
        binding.rvPopularListsThisMonth.adapter=popularListsThisMonthAdapter

        lifecycleScope.launch {

            viewModel.responseFlow.collect{

                popularFilmsThisMonthAdapter.getAdapterList(it)
            }


        }

        lifecycleScope.launch {

            viewModel.recentFriendsReviewResponseFlow.collect{

                recentFriendsReviewAdapter.getAdapterList(it)
            }
        }

        lifecycleScope.launch {

            viewModel.popularListsThisMonthFlow.collect{

                popularListsThisMonthAdapter.getPopularListThisMonthList(it)
            }
        }



    }

//    private fun openGalleryForImage() {
//        val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
//            try {
//
//                saveImageSahredPref(uri)
//
//                binding.imageViewProfileHP.setImageURI(uri)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//
//        galleryLauncher.launch("image/*")
//    }

//    fun saveImageSahredPref(uri:Uri?){
//
//        val editor=ImageSharedPref.edit()
//        editor.putString("profile_image_uri",uri.toString())
//        editor.apply()
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}
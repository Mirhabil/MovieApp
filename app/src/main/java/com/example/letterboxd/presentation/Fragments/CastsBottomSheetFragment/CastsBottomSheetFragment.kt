package com.example.letterboxd.presentation.Fragments.CastsBottomSheetFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.letterboxd.databinding.FragmentCastsBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CastsBottomSheetFragment : BottomSheetDialogFragment() {

    val viewModel:CastsBottomSheetViewModel by viewModels()
    val args:CastsBottomSheetFragmentArgs by navArgs()


    private var _binding:FragmentCastsBottomSheetBinding?=null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentCastsBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getResponse(args.personId)

        lifecycleScope.launch {

            viewModel.response.collectLatest {

                Glide.with(binding.imageViewCastFoto).load("https://image.tmdb.org/t/p/w500"+it?.profile_path).into(binding.imageViewCastFoto)
                binding.textViewCastName.text=it?.name
                binding.textViewCastAge.text=it?.birthday?.split("-")?.first()
                binding.textViewCastBiography.text=it?.biography
                binding.textViewCastCountry.text=it?.place_of_birth
                binding.textViewCastPopularity.text=it?.popularity.toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}
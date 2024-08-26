package com.example.letterboxd.presentation.Fragments.SignUpFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.letterboxd.databinding.FragmentSignupBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment: Fragment() {

    val viewModel:SignUpViewModel by viewModels()



    private var _binding: FragmentSignupBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentSignupBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageViewSignUpQuestionButton.setOnClickListener {

            findNavController().navigate(SignUpFragmentDirections.actionFragmentSignUpToLoginFragment())
        }

        binding.buttonSignUp.setOnClickListener {

            val username=binding.editTextUsernameSignUP.text.toString()
            val email=binding.editTextEmailSignUp.text.toString()
            val password=binding.editTextPasswordSignUp.text.toString()

            viewModel.signUP(username, email, password)



        }

        lifecycleScope.launch {

            viewModel.isLoading.collectLatest {

                binding.progressBar.isVisible=it
            }
        }

        lifecycleScope.launch {

            viewModel.message.filterNotNull().collectLatest {

                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}

data class applicationUsers(
    val username:String=" ",
    val password:String=" "
)
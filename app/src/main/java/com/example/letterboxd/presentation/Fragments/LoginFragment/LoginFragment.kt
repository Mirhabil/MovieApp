package com.example.letterboxd.presentation.Fragments.LoginFragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.letterboxd.databinding.FragmentLoginBinding
import com.example.letterboxd.presentation.Activities.MainActivity.MainActivity
import com.example.letterboxd.presentation.Fragments.SignUpFragment.applicationUsers
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

@AndroidEntryPoint
class LoginFragment:Fragment() {

    //var a by Delegates.notNull<Boolean>()

    val viewModel:LoginViewModel by viewModels()
    val firestore=Firebase.firestore

    private var _binding: FragmentLoginBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val userStateShPref=requireContext().getSharedPreferences("userstate",Context.MODE_PRIVATE)
        //a=userStateShPref.getBoolean("activation",false)

//        if(a){
//            startActivity(Intent(requireContext(), MainActivity::class.java))
//        }

        binding.imageViewLoginQuestionButton.setOnClickListener {

            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToFragmentSignUp())
        }


        binding.buttonLogin.setOnClickListener {

            val email = binding.editTextEmailLogin.text.toString()
            val password = binding.editTextPasswordLogin.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill the gaps!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firestore.collection("users")
                .document(email)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val document = task.result
                        if (document != null && document.exists()) {
                            val data = document.toObject(applicationUsers::class.java)
                            if (data != null && password == data.password) {
                                val shpref = requireContext().getSharedPreferences("userloggedin", Context.MODE_PRIVATE)
                                val editor = shpref.edit()
                                editor.putString("username", data.username)
                                editor.putBoolean("status", true)
                                editor.apply()

                                val userStateShPref=requireContext().getSharedPreferences("userstate",Context.MODE_PRIVATE)
                                userStateShPref.edit().putBoolean("activation",true).apply()

                                val intent = Intent(requireContext(), MainActivity::class.java)
                                startActivity(intent)
                                requireActivity().finish()


                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Invalid password!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "User doesn't exist!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(requireContext(), "Auth failed!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}

package com.example.letterboxd.presentation.Fragments.SignUpFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letterboxd.domain.repository.FireStoreRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SignUpViewModel @Inject constructor(val fireStoreRepo:FireStoreRepo):ViewModel() {

    val message= MutableStateFlow<String?>(null)
    val isLoading= MutableStateFlow(false)

    fun signUP(username:String,email:String,password:String){



        viewModelScope.launch {
            isLoading.update { true }
            message.update { fireStoreRepo.signUP(username, email, password) }
            isLoading.update { false }

        }
    }


}
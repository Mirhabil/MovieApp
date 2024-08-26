package com.example.letterboxd.presentation.Fragments.LoginFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letterboxd.domain.repository.FireStoreRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val fireStoreRepo:FireStoreRepo):ViewModel() {


}
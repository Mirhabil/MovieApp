package com.example.letterboxd.data.remote.repository

import com.example.letterboxd.domain.repository.FireStoreRepo
import com.example.letterboxd.presentation.Fragments.SignUpFragment.applicationUsers
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FireStoreRepoImpl:FireStoreRepo {

    val firestore= Firebase.firestore
    override suspend fun signUP(username: String, email: String, password: String): String {

        return suspendCoroutine {coroutine->

            firestore.collection("users").document(email).set(
                applicationUsers(
                    username, password
                )
            ).addOnSuccessListener { coroutine.resume("Succesful SignUp") }
                .addOnFailureListener { coroutine.resume("Fail SignUP ${it.message}") }
        }

    }

    override suspend fun login(username: String, password: String): String {
        TODO("Not yet implemented")
    }


}
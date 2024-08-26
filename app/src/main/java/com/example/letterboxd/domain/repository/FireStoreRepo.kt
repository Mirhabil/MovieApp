package com.example.letterboxd.domain.repository

interface FireStoreRepo {

    suspend fun signUP(username:String,email:String,password:String):String

    suspend fun login(username: String, password: String): String
}
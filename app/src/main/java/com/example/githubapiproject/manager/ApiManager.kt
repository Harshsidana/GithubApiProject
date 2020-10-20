package com.example.githubapiproject.manager

import com.example.githubapiproject.api.UserListApi
import retrofit2.Retrofit

class ApiManager constructor(private val retrofit: Retrofit) {
    val userListApi by lazy { retrofit.createApi<UserListApi>() }
}

inline fun <reified T> Retrofit.createApi(): T = this.create(T::class.java)
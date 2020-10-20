package com.example.githubapiproject.api

import androidx.annotation.Keep
import com.squareup.moshi.Json
import retrofit2.Call
import retrofit2.http.GET

interface UserListApi {
    @GET("users")
    fun getGitUserList(): Call<ResponseData>
}

data class ResponseData(
    @Json(name = "data") val list: List<Data>

)

data class Data(
    @Json(name = "email") val email:String,
    @Json(name = "first_name") val first_name:String,
    @Json(name = "last_name") val last_name:String,
    @Json(name = "id") val id:String


    )
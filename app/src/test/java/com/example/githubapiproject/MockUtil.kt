package com.example.githubapiproject

import com.example.githubapiproject.api.Data
import com.example.githubapiproject.api.ResponseData
import com.example.githubapiproject.response.GitResponseModel
import retrofit2.Response





object MockUtil {

    val mockGitResponse = GitResponseModel(
        firstName = "test",
        lastName = "last",
        email = "harsh@gmail.com",
        id = "12345"
    )

    val mockDataList = listOf<Data>(
        Data(
            email = "harsh.sidana@gmail.com",
            first_name = "harsh",
            last_name = "Sidana",
            id = "123456"
        )
    )

    fun mockResultSuccess(): Response<ResponseData> {
        val mockResult = Response.success(ResponseData(mockDataList))
        return mockResult
    }


    fun mockResultFail(): Response<ResponseData> {
        val mockResult = Response.success(200, ResponseData(null))
        return mockResult
    }
    fun mockResultFailEmptyList(): Response<ResponseData> {
        val mockResult = Response.success(200, ResponseData(mutableListOf()))
        return mockResult
    }

    fun mockResultEmptyList():MutableList<GitResponseModel>{
        return mutableListOf<GitResponseModel>()

    }
}


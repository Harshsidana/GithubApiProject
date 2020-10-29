package com.example.githubapiproject

import com.example.githubapiproject.api.Data
import com.example.githubapiproject.api.ResponseData
import retrofit2.Response





object MockUtil {
    val mockDataList = listOf<Data>(
        Data(
            email = "pokemon",
            first_name = "harsh",
            last_name = "Shrivastv",
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
}


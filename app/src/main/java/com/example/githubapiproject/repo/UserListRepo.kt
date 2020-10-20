package com.example.githubapiproject.repo

import com.example.githubapiproject.api.ResponseData
import com.example.githubapiproject.entities.ResourceState
import com.example.githubapiproject.response.GitResponseModel

interface UserListRepo{
    suspend fun getUserList(): ResourceState<MutableList<GitResponseModel>>

}

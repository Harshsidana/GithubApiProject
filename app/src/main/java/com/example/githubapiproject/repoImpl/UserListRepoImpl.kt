package com.example.githubapiproject.repoImpl

import com.example.githubapiproject.api.UserListApi
import com.example.githubapiproject.entities.ResourceState
import com.example.githubapiproject.extensions.mapToEntity
import com.example.githubapiproject.repo.UserListRepo
import com.example.githubapiproject.response.GitResponseModel

class UserListRepoImpl(private val userListApi: UserListApi) : UserListRepo {
    override suspend fun getUserList(): ResourceState<MutableList<GitResponseModel>> {
        return userListApi.getGitUserList().mapToEntity {
            val modelList = mutableListOf<GitResponseModel>()
            val data = it.list ?: emptyList()
            data.forEach {
                modelList.add(
                    GitResponseModel(
                        it.first_name,
                        it.last_name,
                        it.id,
                        it.email ?: ""
                    )
                )
            }
            modelList
        }
    }
}
package com.example.githubapiproject.useCases

import com.example.githubapiproject.base.BaseUseCase
import com.example.githubapiproject.base.NetworkUseCase
import com.example.githubapiproject.repo.UserListRepo
import com.example.githubapiproject.response.GitResponseModel
import kotlinx.coroutines.launch

class FetchGitDataUseCase(private val repo: UserListRepo): BaseUseCase< MutableList<GitResponseModel>>() {
    override fun setup(): NetworkUseCase< MutableList<GitResponseModel>> {
        launch {
            execute(repo.getUserList())
        }
        return this
    }


}
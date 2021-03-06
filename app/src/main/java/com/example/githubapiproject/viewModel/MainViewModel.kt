package com.example.githubapiproject.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.githubapiproject.base.BaseNetworkViewModel
import com.example.githubapiproject.entities.ResourceState
import com.example.githubapiproject.response.GitResponseModel
import com.example.githubapiproject.useCases.FetchGitDataUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val fetchGitDataUseCase: FetchGitDataUseCase
) : BaseNetworkViewModel(fetchGitDataUseCase) {

    fun getUserData() {
        fetchGitDataUseCase.setup()
    }

    val resultLiveData= fetchGitDataUseCase.getResultLiveData()
}
package com.example.githubapiproject.base

import androidx.lifecycle.MutableLiveData
import com.example.githubapiproject.entities.ResourceState

abstract class BaseUseCase<R> : NetworkUseCase<R>() {
    private val liveData by lazy { MutableLiveData<ResourceState<R>>() }


    fun execute(parameter: ResourceState<R>) {
        parseResponseData(parameter)
    }

    private fun parseResponseData(parameter: ResourceState<R>) {
        liveData.postValue(parameter)
    }


    fun getResultLiveData() = liveData
}

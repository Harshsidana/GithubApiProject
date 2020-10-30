package com.example.githubapiproject.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

open class BaseNetworkViewModel(
    private vararg val baseUseCases: NetworkUseCase<*>
) : ViewModel() {
    override fun onCleared() {
        super.onCleared()
        baseUseCases.map {
            it.cancel()
        }
    }
}
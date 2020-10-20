package com.example.githubapiproject.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel

open class BaseNetworkViewModel(
    app: Application,
    private vararg val baseUseCases: NetworkUseCase<*>
) : AndroidViewModel(app) {
    override fun onCleared() {
        super.onCleared()
        baseUseCases.map {
            it.cancel()
        }
    }
}
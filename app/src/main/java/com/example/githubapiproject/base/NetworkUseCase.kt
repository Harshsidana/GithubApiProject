package com.example.githubapiproject.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


abstract class NetworkUseCase<R>:CoroutineScope {

    private var job= Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO+job
    abstract fun setup(): NetworkUseCase<R>
    fun cancel() {
        job.cancel()
    }
}
package com.example.githubapiproject.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.githubapiproject.entities.ResourceState
import retrofit2.Call

fun <F, T> Call<F>.mapToEntity(
    mapper: (F) -> T
): ResourceState<T> {
    val response = this.execute()
    return if (response.isSuccessful) {
        val body = response.body()
        ResourceState.Success(mapper(body as F), response.code())
    } else {
        return ResourceState.Failure(java.lang.Exception("something went wrong"))
    }
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>?, action: (t: T) -> Unit) {
    liveData?.observe(this, Observer { it?.let { t -> action(t) } })
}
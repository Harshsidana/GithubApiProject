package com.example.githubapiproject.entities

sealed class ResourceState<T> {
    data class Success<T>(val body: T, val code: Int = 200) : ResourceState<T>()

    data class Failure<T>(
        val exception: Throwable,
        val code: Int = 500
    ) : ResourceState<T>()
}
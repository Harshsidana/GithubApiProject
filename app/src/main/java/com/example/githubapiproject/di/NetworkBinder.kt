package com.example.githubapiproject.di

import com.example.githubapiproject.api.UserListApi
import com.example.githubapiproject.manager.ApiManager
import com.example.githubapiproject.repo.UserListRepo
import com.example.githubapiproject.repoImpl.UserListRepoImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
@Module
class NetworkBinder {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()
    @Singleton
    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    @Singleton
    @Provides
    fun provideMobileVerificationApi(apiManager: ApiManager): UserListApi = apiManager.userListApi
    @Provides
    @Singleton
    fun provideApiManager(retrofit: Retrofit): ApiManager = ApiManager(retrofit)

    @Singleton
    @Provides
    fun provideUserListRepo(userListAPi: UserListApi): UserListRepo = UserListRepoImpl(userListAPi)

}
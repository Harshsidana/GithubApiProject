package com.example.githubapiproject.di

import com.example.githubapiproject.repo.UserListRepo
import com.example.githubapiproject.useCases.FetchGitDataUseCase
import dagger.Module
import dagger.Provides
@Module
class UseCaseBinder {
    @Provides
    fun provideSignUpUseCase(listRepo: UserListRepo) =
        FetchGitDataUseCase(listRepo)
}
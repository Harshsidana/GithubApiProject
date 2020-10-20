package com.example.githubapiproject.di

import com.example.githubapiproject.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBinder {

    @ContributesAndroidInjector()
    abstract fun bindLoginActivity(): MainActivity
}
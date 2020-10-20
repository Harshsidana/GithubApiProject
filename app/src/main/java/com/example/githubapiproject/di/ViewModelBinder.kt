package com.example.githubapiproject.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubapiproject.viewModel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelBinder {
    @Binds
    @Singleton
    abstract fun providesViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun providesMainViewModel(mainViewModel: MainViewModel): ViewModel
}
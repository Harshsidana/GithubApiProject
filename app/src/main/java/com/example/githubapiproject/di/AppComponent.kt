package com.example.githubapiproject.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBinder::class,
        UseCaseBinder::class,
        NetworkBinder::class,
        ViewModelBinder::class
    ]
)
interface AppComponent : AndroidInjector<com.example.githubapiproject.Application> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
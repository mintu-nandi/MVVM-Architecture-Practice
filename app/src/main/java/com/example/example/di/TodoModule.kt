package com.example.example.di

import com.example.example.api.ApiService
import com.example.example.api.datasource.MainDataSource
import com.example.example.repository.MainRepository
import com.example.example.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object TodoModule {

    @ActivityScoped
    @Provides
    fun provideAuthenticationDataSource(apiService: ApiService): MainDataSource {
        return MainDataSource(
            apiService
        )
    }

    @ActivityScoped
    @Provides
    fun provideAuthenticationRepository(mainDataSource: MainDataSource): MainRepository {
        return MainRepository(
            mainDataSource
        )
    }

    @ActivityScoped
    @Provides
    fun provideAuthenticationFactory(mainRepository: MainRepository): MainViewModelFactory {
        return MainViewModelFactory(
            mainRepository
        )
    }

//    @ActivityScoped
//    @Provides
//    fun provideAuthenticationViewModel(propertiesRepository: MainRepository): MainViewModel {
//        return MainViewModel(propertiesRepository)
//    }
}
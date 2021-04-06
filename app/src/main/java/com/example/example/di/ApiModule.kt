package com.example.example.di

import android.content.Context
import android.content.SharedPreferences
import com.example.example.DemoApp
import com.example.example.api.RetrofitBuilder
import com.example.example.api.ApiService
import com.example.example.util.SharedPreferencesUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideAppContext(): DemoApp {
        return DemoApp()
    }

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SharedPreferencesUtil.preferencesName, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideApiServiceInterface(): ApiService {
        return RetrofitBuilder.apiService
    }
}

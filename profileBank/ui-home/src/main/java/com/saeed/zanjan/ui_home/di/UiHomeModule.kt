package com.saeed.zanjan.ui_home.di

import com.saeed.zanjan.interactor.GetData
import com.saeed.zanjan.interactor.Interactor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UiHomeModule {


    @Provides
    @Singleton
    fun provideGetData(
        interactor:Interactor
    ):GetData{
        return interactor.getData
    }
}
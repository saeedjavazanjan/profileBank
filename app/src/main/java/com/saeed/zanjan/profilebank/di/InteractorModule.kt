package com.saeed.zanjan.profilebank.di

import com.saeed.zanjan.interactor.Interactor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorModule {


    @Provides
    @Singleton
    fun provideInteraactor():Interactor{

        return Interactor.build()

    }
}
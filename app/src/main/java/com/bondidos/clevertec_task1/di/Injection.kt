package com.bondidos.clevertec_task1.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object Injection {

    @Provides
    fun provideContext(@ActivityContext context: Context): Context = context
}
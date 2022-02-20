package com.bondidos.clevertec_task1.presentation.di

import android.content.Context
import androidx.room.Room
import com.bondidos.clevertec_task1.data.room.ContactsDataBase
import com.bondidos.clevertec_task1.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun provideContext(@ActivityContext context: Context): Context = context
}

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideRoomDatabaseDao(@ApplicationContext context: Context): Repository =
        Room.databaseBuilder(
            context,
            ContactsDataBase::class.java,
            "database"
        ).build().contactsDB()
}
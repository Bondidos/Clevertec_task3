package com.bondidos.clevertec_task1.di

import android.content.Context
import androidx.room.Room
import com.bondidos.clevertec_task1.data.room.ContactsDao
import com.bondidos.clevertec_task1.data.room.ContactsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object Injection {

    @Provides
    fun provideContext(@ActivityContext context: Context): Context = context

    @Singleton
    @Provides
    fun provideRoomDatabaseDao(@ApplicationContext context: Context): ContactsDao =
        Room.databaseBuilder(
            context,
            ContactsDataBase::class.java,
            "database"
        ).build().contactsDB()
}

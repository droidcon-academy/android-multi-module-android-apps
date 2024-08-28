package com.droidcon.droidynote.di

import com.droidcon.droidynote.AppDataBase
import com.droidcon.droidynote.data.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun provideNotesDao(
        database: AppDataBase
    ): NoteDao = database.noteDao()

}
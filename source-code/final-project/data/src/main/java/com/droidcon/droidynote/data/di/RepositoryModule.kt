package com.droidcon.droidynote.data.di

import com.droidcon.droidynote.data.repository.NoteRepositoryImpl
import com.droidcon.droidynote.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(
        noteRepositoryImpl: NoteRepositoryImpl
    ): NoteRepository
}
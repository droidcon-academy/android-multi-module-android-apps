package com.droidcon.droidynote.domain.repository

import com.droidcon.droidynote.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotes(): Flow<List<Note>>
    suspend fun getNoteById(id: Int): Note?
    suspend fun insertOrUpdateNote(note: Note)
    suspend fun deleteNote(note: Note)
}
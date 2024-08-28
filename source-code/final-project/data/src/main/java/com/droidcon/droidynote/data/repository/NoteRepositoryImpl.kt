package com.droidcon.droidynote.data.repository

import com.droidcon.droidynote.data.dao.NoteDao
import com.droidcon.droidynote.data.enitity.NoteEntity
import com.droidcon.droidynote.data.enitity.toDomainModel
import com.droidcon.droidynote.data.enitity.toRoomEntity
import com.droidcon.droidynote.domain.model.Note
import com.droidcon.droidynote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {

    override fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes()
        .map { it.map(NoteEntity::toDomainModel) }


    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)?.toDomainModel()
    }

    override suspend fun insertOrUpdateNote(note: Note) {
        return noteDao.insertOrUpdateNote(note.toRoomEntity())
    }

    override suspend fun deleteNote(note: Note) {
        return noteDao.deleteNote(note.toRoomEntity())
    }

}
package com.droidcon.droidynote.shared.testing.fake

import com.droidcon.droidynote.domain.model.Note
import com.droidcon.droidynote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.Date

class FakeNoteRepository : NoteRepository {

    private val notesData: MutableList<Note> = mutableListOf(
        Note(
            id = 1,
            title = "",
            content = "",
            createdAt = Date(),
            updatedAt = Date()
        )
    )

    override fun getAllNotes(): Flow<List<Note>> = flowOf(notesData)

    override suspend fun getNoteById(id: Int): Note? {
        return notesData.firstOrNull { it.id == id }
    }

    override suspend fun insertOrUpdateNote(note: Note) {
        val existingNoteIndex = notesData.indexOfFirst { it.id == note.id }
        if (existingNoteIndex != -1) {
            notesData[existingNoteIndex] = note
        } else {
            notesData.add(note)
        }
    }

    override suspend fun deleteNote(note: Note) {
        notesData.removeIf { it.id == note.id }
    }
}
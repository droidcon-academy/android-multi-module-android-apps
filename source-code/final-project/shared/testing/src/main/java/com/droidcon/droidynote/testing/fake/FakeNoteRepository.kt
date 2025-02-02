package com.droidcon.droidynote.testing.fake

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.Date

class FakeNoteRepository : com.droidcon.droidynote.domain.repository.NoteRepository {

    private val notesData: MutableList<com.droidcon.droidynote.domain.model.Note> = mutableListOf(
        com.droidcon.droidynote.domain.model.Note(
            id = 1,
            title = "",
            content = "",
            createdAt = Date(),
            updatedAt = Date()
        )
    )
    override fun getAllNotes(): Flow<List<com.droidcon.droidynote.domain.model.Note>> = flowOf(notesData)

    override suspend fun getNoteById(id: Int): com.droidcon.droidynote.domain.model.Note? {
        return notesData.firstOrNull { it.id == id }
    }

    override suspend fun insertOrUpdateNote(note: com.droidcon.droidynote.domain.model.Note) {
        val existingNoteIndex = notesData.indexOfFirst { it.id == note.id }
        if (existingNoteIndex != -1) {
            notesData[existingNoteIndex] = note
        } else {
            notesData.add(note)
        }
    }

    override suspend fun deleteNote(note: com.droidcon.droidynote.domain.model.Note) {
        notesData.removeIf { it.id == note.id }
    }
}
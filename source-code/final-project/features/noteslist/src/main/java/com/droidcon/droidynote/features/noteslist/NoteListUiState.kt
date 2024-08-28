package com.droidcon.droidynote.features.noteslist

import com.droidcon.droidynote.domain.model.Note

internal data class NoteListUiState(
    val isLoading: Boolean = true,
    val selectedNote: Note? = null,
    val notes: List<Note> = emptyList()
)

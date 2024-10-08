package com.droidcon.droidynote.ui.screens.list

import com.droidcon.droidynote.domain.model.Note

data class NoteListUiState(
    val isLoading: Boolean = true,
    val selectedNote: Note? = null,
    val notes: List<Note> = emptyList()
)

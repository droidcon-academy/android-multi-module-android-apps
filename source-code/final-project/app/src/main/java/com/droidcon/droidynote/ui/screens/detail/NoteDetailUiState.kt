package com.droidcon.droidynote.ui.screens.detail

import com.droidcon.droidynote.domain.model.Note
import java.util.Date

data class NoteDetailUiState(
    val isLoading: Boolean = true,
    val note: Note,
    val noteTitle: String = "",
    val noteContent: String = "",
) {
    val canSave: Boolean
        get() = (noteTitle.isNotBlank() || noteContent.isNotBlank()) &&
                note.title != noteTitle || note.content != noteContent
}

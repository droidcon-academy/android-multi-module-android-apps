package com.droidcon.droidynote.features.notedetail

internal data class NoteDetailUiState(
    val isLoading: Boolean = true,
    val note: com.droidcon.droidynote.domain.model.Note,
    val noteTitle: String = "",
    val noteContent: String = "",
) {
    val canSave: Boolean
        get() = (noteTitle.isNotBlank() || noteContent.isNotBlank()) &&
                note.title != noteTitle || note.content != noteContent
}

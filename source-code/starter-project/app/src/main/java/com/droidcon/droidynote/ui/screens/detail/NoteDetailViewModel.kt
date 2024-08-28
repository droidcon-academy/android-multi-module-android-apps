package com.droidcon.droidynote.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcon.droidynote.domain.model.Note
import com.droidcon.droidynote.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val noteRepository: NoteRepository
) : ViewModel() {


    private val _uiState = MutableStateFlow(NoteDetailUiState(note = initializeNewNote()))
    val uiState: StateFlow<NoteDetailUiState> = _uiState.asStateFlow()

    init {
        getNote()
    }

    private fun getNote() {
        viewModelScope.launch {
            val noteId = savedStateHandle.get<Int?>("noteId")
            val note = noteId?.let { noteRepository.getNoteById(it) } ?: initializeNewNote()

            _uiState.update {
                it.copy(
                    isLoading = false,
                    note = note,
                    noteTitle = note.title,
                    noteContent = note.content,
                )
            }

        }
    }

    fun setNoteTitle(title: String) {
        _uiState.update {
            it.copy(
                noteTitle = title
            )
        }
    }

    fun setNoteContent(content: String) {
        _uiState.update {
            it.copy(
                noteContent = content
            )
        }
    }

    fun saveNote() {
        viewModelScope.launch {
            if (_uiState.value.canSave) {
                val note = with(_uiState.value) {
                    Note(
                        id = note.id,
                        title = noteTitle,
                        content = noteContent,
                        createdAt = note.createdAt,
                        updatedAt = Date()
                    )
                }

                noteRepository.insertOrUpdateNote(note)
            }
        }
    }

    private fun initializeNewNote(): Note {
        return Note(
            id = 0,
            title = "",
            content = "",
            createdAt = Date(),
            updatedAt = Date()
        )
    }
}
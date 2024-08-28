package com.droidcon.droidynote.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcon.droidynote.domain.model.Note
import com.droidcon.droidynote.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<NoteListUiState> = MutableStateFlow(NoteListUiState())
    val uiState: StateFlow<NoteListUiState> = noteRepository.getAllNotes()
        .combine(_uiState) { notes, state ->
            state.copy(
                isLoading = false,
                notes = notes
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = NoteListUiState()
        )


    fun selectNote(note: Note) {
        _uiState.update {
            it.copy(
                selectedNote = note
            )
        }
    }

    fun deleteSelectedNote() {
        viewModelScope.launch {
            _uiState.value.selectedNote?.let { noteRepository.deleteNote(it) }
            _uiState.update {
                it.copy(
                    selectedNote = null
                )
            }
        }
    }

    fun unSelectNote() {
        _uiState.update {
            it.copy(
                selectedNote = null
            )
        }
    }

}
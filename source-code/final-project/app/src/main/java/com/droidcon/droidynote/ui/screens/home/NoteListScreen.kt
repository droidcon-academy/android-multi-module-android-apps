package com.droidcon.droidynote.ui.screens.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidcon.droidynote.domain.model.Note
import com.droidcon.droidynote.ui.shared.LoadingView
import com.droidcon.droidynote.ui.shared.NoteItemView
import kotlinx.serialization.Serializable

@Serializable
object NoteListRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    onNavigateToDetail: (Int?) -> Unit,
    viewModel: NoteListViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler(enabled = uiState.selectedNote != null) {
        viewModel.unSelectNote()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "DroidyNote")
                },
                actions = {
                    if (uiState.selectedNote != null) {
                        IconButton(onClick = viewModel::deleteSelectedNote) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete note"
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigateToDetail(null) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add a note"
                )
            }
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(contentPadding)
                .padding(contentPadding)
        ) {
            if (uiState.isLoading) {
                LoadingView()
            } else if (uiState.notes.isEmpty()) {
                EmptyHomeScreenContent()
            } else {
                HomeScreenContent(
                    uiState = uiState,
                    onNoteClick = onNavigateToDetail,
                    onUnselectNote = viewModel::unSelectNote,
                    onSelectNote = viewModel::selectNote,
                )
            }
        }
    }
}

@Composable
fun EmptyHomeScreenContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No notes yet")
    }
}

@Composable
fun HomeScreenContent(
    uiState: NoteListUiState,
    onNoteClick: (Int) -> Unit,
    onSelectNote: (Note) -> Unit,
    onUnselectNote: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(uiState.notes, key = { it.id }) { note ->
            Modifier.fillMaxSize()
            NoteItemView(
                note = note,
                isSelected = uiState.selectedNote == note,
                onClick = {
                    if (uiState.selectedNote == note) {
                        onUnselectNote()
                    } else {
                        onNoteClick(note.id)
                    }
                },
                onLongClick = { onSelectNote(note) },
                modifier = Modifier.animateItem()
            )
        }
    }
}
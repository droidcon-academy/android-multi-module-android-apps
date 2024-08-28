package com.droidcon.droidynote.features.notedetail

import androidx.lifecycle.SavedStateHandle
import com.droidcon.droidynote.shared.testing.fake.FakeNoteRepository
import com.droidcon.droidynote.shared.testing.rules.MainDispatcherRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NoteDetailViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val noteRepository: com.droidcon.droidynote.domain.repository.NoteRepository =
        FakeNoteRepository()


    private lateinit var viewModel: NoteDetailViewModel


    @Test
    fun `when note is loaded then ui state contains note`() = runTest {
        val savedStateHandle = SavedStateHandle(mapOf(NoteDetailRoute::noteId.name to 1))
        viewModel = NoteDetailViewModel(
            savedStateHandle,
            noteRepository
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        val expectedNote = noteRepository.getNoteById(1)

        with(viewModel.uiState.value) {
            assertEquals(expectedNote, note)
            assertEquals(false, isLoading)
        }

        collectJob.cancel()
    }

    @Test
    fun `when note id is null then create a new note`() = runTest {
        val savedStateHandle = SavedStateHandle(mapOf(NoteDetailRoute::noteId.name to null))
        viewModel = NoteDetailViewModel(
            savedStateHandle,
            noteRepository
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        with(viewModel.uiState.value) {
            assertTrue("New note id should be 0", note.id == 0)
            assertTrue("New note title should be empty", note.title.isEmpty())
            assertTrue("New note content should be empty", note.content.isEmpty())
            assertEquals(false, isLoading)
        }

        collectJob.cancel()
    }

    @Test
    fun `when note is saved then it is added to the repository`() = runTest {
        val savedStateHandle = SavedStateHandle(mapOf(NoteDetailRoute::noteId.name to null))
        viewModel = NoteDetailViewModel(
            savedStateHandle,
            noteRepository
        )
        viewModel.setNoteTitle("Test Title")
        viewModel.setNoteContent("Test Content")
        viewModel.saveNote()

        val notesList = noteRepository.getAllNotes().first()
        val expectedNote = notesList.last()

        assertEquals("Test Title", expectedNote.title)
        assertEquals("Test Content", expectedNote.content)
    }

    @Test
    fun `when note is updated then it is updated in the repository`() = runTest {
        val noteId = 1
        val savedStateHandle = SavedStateHandle(mapOf(NoteDetailRoute::noteId.name to noteId))
        viewModel = NoteDetailViewModel(
            savedStateHandle,
            noteRepository
        )

        viewModel.setNoteTitle("Updated Title")
        viewModel.setNoteContent("Updated Content")

        viewModel.saveNote()

        val expectedNote = noteRepository.getNoteById(noteId)

        assertEquals("Updated Title", expectedNote?.title)
        assertEquals("Updated Content", expectedNote?.content)
    }
}
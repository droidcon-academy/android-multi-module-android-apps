package com.droidcon.droidynote.ui.screens.home

import com.droidcon.droidynote.domain.repository.NoteRepository
import com.droidcon.droidynote.fake.FakeNoteRepository
import com.droidcon.droidynote.rules.MainDispatcherRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class NoteListViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val noteRepository: NoteRepository = FakeNoteRepository()

    private lateinit var viewModel: NoteListViewModel

    @Before
    fun setUp() {
        viewModel = NoteListViewModel(noteRepository)
    }


    @Test
    fun stateIsInitial() = runTest {
        val initialState = NoteListUiState()
        assertEquals(initialState, viewModel.uiState.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when notes are loaded then ui state contains notes`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        val uiState = viewModel.uiState.value

        assertFalse("Loading is supposed to be false",uiState.isLoading)
        assertTrue("Notes list is supposed to not be empty",uiState.notes.isNotEmpty())

        collectJob.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when select note the note is selected`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        val noteToSelect = viewModel.uiState.value.notes.first()
        viewModel.selectNote(noteToSelect)
        assertEquals(noteToSelect,viewModel.uiState.value.selectedNote)

        collectJob.cancel()
    }

}
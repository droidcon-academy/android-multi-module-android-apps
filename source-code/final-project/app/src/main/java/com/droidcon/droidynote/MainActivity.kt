package com.droidcon.droidynote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.droidcon.droidynote.features.notedetail.NoteDetailRoute
import com.droidcon.droidynote.features.notedetail.noteDetailScreen
import com.droidcon.droidynote.features.noteslist.NoteListRoute
import com.droidcon.droidynote.features.noteslist.noteListScreen
import com.droidcon.droidynote.shared.ui.theme.DroidyNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DroidyNoteTheme {
                Box(modifier = Modifier.safeDrawingPadding()) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NoteListRoute
                    ) {

                        noteListScreen(
                            onNavigateToDetail = { noteId ->
                                navController.navigate(NoteDetailRoute(noteId))
                            }
                        )

                        noteDetailScreen(
                            onNavigateBack = navController::navigateUp
                        )
                    }
                }
            }
        }
    }
}
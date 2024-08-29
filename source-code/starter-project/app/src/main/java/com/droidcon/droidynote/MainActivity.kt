package com.droidcon.droidynote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.droidcon.droidynote.ui.screens.detail.NoteDetailRoute
import com.droidcon.droidynote.ui.screens.detail.NoteDetailScreen
import com.droidcon.droidynote.ui.screens.list.NoteListRoute
import com.droidcon.droidynote.ui.screens.list.NoteListScreen
import com.droidcon.droidynote.ui.theme.DroidyNoteTheme
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
                        composable<NoteListRoute> {
                            NoteListScreen(
                                onNavigateToDetail = { noteId ->
                                    navController.navigate(NoteDetailRoute(noteId))
                                }
                            )
                        }

                        composable<NoteDetailRoute> {
                            NoteDetailScreen(
                                onNavigateBack = navController::navigateUp
                            )
                        }
                    }
                }
            }
        }
    }
}
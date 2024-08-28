package com.droidcon.droidynote.shared.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidcon.droidynote.domain.model.Note
import com.droidcon.droidynote.shared.ui.theme.DroidyNoteTheme
import java.util.Date

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteItemView(
    note: Note,
    isSelected: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val primaryColor = MaterialTheme.colorScheme.primary
    val defaultBorder = CardDefaults.outlinedCardBorder()

    val borderColor by remember(isSelected) {
        derivedStateOf {
            if (isSelected) BorderStroke(2.dp, primaryColor) else defaultBorder
        }
    }

    OutlinedCard(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .combinedClickable(
                enabled = true,
                onClick = onClick,
                onLongClick = onLongClick
            ),
        border = borderColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            if (note.title.isNotEmpty()) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            if (note.content.isNotEmpty()) {
                Text(
                    text = note.content,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview
@Composable
private fun NoteItemPreview() {
    DroidyNoteTheme {
        NoteItemView(
            isSelected = false,
            note = Note(
                id = 1,
                title = "My note",
                content = "Hello this is my preview note",
                createdAt = Date(),
                updatedAt = Date()
            ),
            onClick = {},
            onLongClick = {}
        )
    }
}
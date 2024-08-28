package com.droidcon.droidynote.data.enitity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.droidcon.droidynote.domain.model.Note
import java.util.Date

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val createdAt: Long,
    val updatedAt: Long
)

fun NoteEntity.toDomainModel() : Note {
    return Note(
        id = id,
        title = title,
        content = content,
        createdAt = Date(createdAt),
        updatedAt = Date(updatedAt)
    )
}

fun Note.toRoomEntity() : NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt.time,
        updatedAt = updatedAt.time
    )
}

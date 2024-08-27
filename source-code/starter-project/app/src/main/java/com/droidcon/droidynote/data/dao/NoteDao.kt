package com.droidcon.droidynote.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.droidcon.droidynote.data.enitity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Upsert
    suspend fun insertOrUpdateNote(note: NoteEntity)

    @Query("SELECT * FROM notes ORDER by updatedAt DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteEntity?

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

}
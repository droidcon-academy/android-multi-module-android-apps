package com.droidcon.droidynote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.droidcon.droidynote.data.dao.NoteDao
import com.droidcon.droidynote.data.enitity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        const val DATABASE_NAME = "droidynote"
    }
}
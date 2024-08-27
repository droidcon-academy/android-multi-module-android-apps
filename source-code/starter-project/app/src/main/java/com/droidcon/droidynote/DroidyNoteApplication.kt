package com.droidcon.droidynote

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DroidyNoteApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
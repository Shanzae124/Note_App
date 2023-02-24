package com.example.noteapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
//@HiltAndroidApp : give access to entire application to hilt
// and now hilt can provide dependency to entire app which we want
//and to make it official we add application in android manifest to
// define that it is dependency container at the application level
@HiltAndroidApp
class NoteApplication : Application() {
}
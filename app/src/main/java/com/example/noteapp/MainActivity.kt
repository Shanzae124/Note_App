package com.example.noteapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteapp.data.NoteDataSource
import com.example.noteapp.model.Note
import com.example.noteapp.screens.NoteApp
import com.example.noteapp.screens.NoteScreen
import com.example.noteapp.screens.NoteViewModel
import com.example.noteapp.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint
//@AndroidEntryPoint: it will tell that it is a dependency container and
// we can get the dependencies from here
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                Surface(color = MaterialTheme.colors.background)
                {

                   NoteApp()
                    }
                }

            }
        }
    }



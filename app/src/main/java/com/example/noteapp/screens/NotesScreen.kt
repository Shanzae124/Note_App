package com.example.noteapp.screens

import android.os.Build
import android.text.format.DateFormat
import android.widget.Space
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteapp.R
import com.example.noteapp.components.NoteButton
import com.example.noteapp.components.NoteInputText
import com.example.noteapp.data.NoteDataSource
import com.example.noteapp.model.Note
import com.example.noteapp.util.DateConverter
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Date


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteApp(noteViewModel: NoteViewModel = viewModel()){
        val notesList = noteViewModel.noteUiState.collectAsState().value
    NoteScreen(
        note = notesList ,
        onAddNote = {
            noteViewModel.add(it)
        } ,
        onRemoveNote = {
            noteViewModel.remove(it)
        })
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteScreen(
    note: List<Note>,
    onAddNote : (Note) ->Unit,
    onRemoveNote : (Note) ->Unit,
){
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
val context = LocalContext.current
    Column(modifier = Modifier.padding(8.dp)) {
        TopAppBar(
            title = { Text(stringResource(R.string.app_name)) },
        actions = {
            Icon(imageVector = Icons.Default.Notifications ,
            contentDescription = "notifications"
            )
        } )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                text = title,
                onTextChange = {
                    newTitle -> title = newTitle
                               },
                label = "Title",
                modifier = Modifier.padding(
                    top = 12.dp,
                    bottom = 12.dp
                )
            )
            NoteInputText(
                text = description,
                onTextChange = {
                            newDescription -> description = newDescription
                    },
                label = "note",
                modifier = Modifier.padding(
                    bottom = 12.dp
                )
            )
            NoteButton(
                onClick = {
                          if (title.isNotEmpty() && description.isNotEmpty()){
                              onAddNote(Note(title = title, description = description))
                              title = ""
                              description = ""
                              Toast.makeText(context , "note added"
                              ,Toast.LENGTH_SHORT).show()
                          }
                },
                text = "add a note",
            )
        }
        Spacer(modifier = Modifier.padding(12.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(2) )  {
           items(note){
               note->NoteRow(
               note = note ,
               onNoteClicked = { onRemoveNote(it) })
           }
    }
}
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
onNoteClicked : (Note) -> Unit
){
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(4.dp)
            .clip(
                shape = RoundedCornerShape(
                    topEnd = 12.dp,
                    bottomStart = 12.dp
                )
            )
            .fillMaxWidth().fillMaxSize()
        ) {
    Column(
        modifier = Modifier
            .padding(
                horizontal = 14.dp,
                vertical = 16.dp
            ),
        horizontalAlignment = Alignment.Start
    ) {
        Text(note.title,
        style = MaterialTheme.typography.subtitle2)
        Text(note.description , style = MaterialTheme.typography.subtitle1)
        Text(text = note.entryDate.toString(),
            style = MaterialTheme.typography.caption)
        Row() {
            IconButton(onClick = { onNoteClicked(note) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "delete",
                tint = Color.Red)
            }
        }
    }
}
}

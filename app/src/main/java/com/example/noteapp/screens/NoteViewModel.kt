package com.example.noteapp.screens

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.model.Note
import com.example.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

//to get the data from repository to view model we have to inject repository in MVVM
@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    private val _noteUiState = MutableStateFlow<List<Note>>(emptyList())
    val noteUiState = _noteUiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect{
                listOfNotes->
                if (listOfNotes.isNullOrEmpty()) {
                    Log.d("Empty", ": Empty list")
                }else {
                    _noteUiState.value = listOfNotes
                }
            }
        }
    }
     fun add(note: Note) = viewModelScope.launch { repository.addNote(note) }
    fun remove(note: Note) = viewModelScope.launch { repository.deleteNote(note) }
     fun update(note: Note) = viewModelScope.launch { repository.updateNote(note) }
}
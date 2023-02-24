package com.example.noteapp.repository

import com.example.noteapp.data.NoteDataBaseDao
import com.example.noteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

//to get data from dao to repository we have to inject dao in the repository
 class NoteRepository @Inject constructor(private val noteDataBaseDao: NoteDataBaseDao){
    suspend fun addNote(note:Note) = noteDataBaseDao.insertNote(note)
    suspend fun updateNote(note:Note) = noteDataBaseDao.updateNote(note)
    suspend fun deleteNote(note: Note) = noteDataBaseDao.deleteNote(note)
    suspend fun deleteAllNotes() = noteDataBaseDao.deleteAll()
// we don't need to use suspend function here as
// we are using flow so it will take care of background by itself
    fun getAllNotes(): Flow<List<Note>> = noteDataBaseDao.getNotes().flowOn(
        Dispatchers.IO).conflate()
}

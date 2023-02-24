package com.example.noteapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.model.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDataBaseDao{
//    we give a block of code which works synchronously and whenever there is a problem or something to change
//    so the process can suspend , stop or relaunch that are synchronized
//    suspend is used that if during fetching of data if the process get stuck somewhere and takes too much time
//    so it should suspend all the fun or relaunched

//    to get this work fine we wrap list note
//    flow is asynchronous and will wait until the first step is finished so
//    it's very beneficial in room data base
    @Query("SELECT * from notes_tbl")
     fun getNotes() : Flow<List<Note>>

    @Query("SELECT * from notes_tbl where id =:id")
    suspend fun getNotesById(id : String) : Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Query("DELETE from notes_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: Note)
}

package com.example.noteapp.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.*
@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "notes_tbl")
data class Note(
//    UUID = Universal Unique Identifier
//  UUID.randomUUID() generates random id and id's will never be the same which we need in this case
    @PrimaryKey
    val id: UUID= UUID.randomUUID(),

    @ColumnInfo(name = "note_title")
    val title: String,

    @ColumnInfo(name = "note_description")
    val description: String,

    @ColumnInfo(name = "note_entryDate")
    val entryDate: Date = Date.from(Instant.now())
        )

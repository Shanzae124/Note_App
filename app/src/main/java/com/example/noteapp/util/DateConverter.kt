package com.example.noteapp.util

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    @TypeConverter
    fun getDateFrom( date: Date) : Long{
        return date.time
    }

    @TypeConverter
    fun getTimeFrom(timeStamp : Long) : Date? {
        return Date(timeStamp)
    }
}
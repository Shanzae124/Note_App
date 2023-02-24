package com.example.noteapp.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.noteapp.data.NoteDataBase
import com.example.noteapp.data.NoteDataBaseDao
import com.example.noteapp.model.Note
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//tell hilt how to provide instance of different types
//to create RoomDataBase we would need a builder to create an instance of that
//database as we don't want to create a  database every time the app starts

@RequiresApi(Build.VERSION_CODES.O)
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideNotesData(noteDataBase: NoteDataBase) : NoteDataBaseDao
    =noteDataBase.noteDao()

    @Singleton
    @Provides
    fun provideNotesDatabase(@ApplicationContext context: Context) : NoteDataBase
    =Room.databaseBuilder(context,NoteDataBase::class.java,"notes_db").fallbackToDestructiveMigration().build()
}
package com.aldimas.notesapp.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aldimas.notesapp.data.entity.Notes
import com.aldimas.notesapp.data.room.Converter
import com.aldimas.notesapp.data.room.NotesDao

class NotesRepository(private val notesDao: NotesDao) {
    fun getAllData(): LiveData<List<Notes>> = notesDao.getAllData()

    suspend fun insertNotes(notes: Notes) {
        notesDao.insertNotes(notes)
    }

    // single Expression kotlin
    fun sortByHighPriority(): LiveData<List<Notes>> = notesDao.sortByHighPriority()
    fun sortByLowPriority(): LiveData<List<Notes>> = notesDao.sortByLowPriority()

    suspend fun deleteAllData() = notesDao.deleteAllData()

    fun searchByQuery(query: String): LiveData<List<Notes>> {
        return notesDao.searchByQuery(query)
    }

    suspend fun deleteNotes(notes: Notes) = notesDao.deleteNote(notes)

    suspend fun updateNotes(notes: Notes) = notesDao.updateNote(notes)
}
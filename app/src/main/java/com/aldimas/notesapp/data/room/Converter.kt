package com.aldimas.notesapp.data.room

import androidx.room.TypeConverter
import com.aldimas.notesapp.data.entity.Priority

class Converter {

    //fungsi ini di panggil ketika get sebuah database
    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }
    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}
package com.manpreet.note.repository.roomdatabase.databaseUtils

import androidx.room.TypeConverter
import com.manpreet.note.repository.roomdatabase.entity.Priority

/**
 * ROOM do not allow object references between entities, only allow primitive and boxed types
 * This class is created to hold to changes in custom data types
 * to represent columns of database Table.
 *
 * */
class Converter{
    @TypeConverter
    fun fromStringToPriority(priorityToGet: String): Priority {
        return Priority.valueOf(priorityToGet)
    }
    @TypeConverter
    fun fromPriorityToString( priorityToString: Priority): String{
        return priorityToString.name
    }
}
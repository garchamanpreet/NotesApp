
package com.manpreet.note.repository.roomdatabase.database

import android.content.Context
import androidx.room.*
import com.manpreet.note.repository.roomdatabase.dao.NoteDao
import com.manpreet.note.repository.roomdatabase.databaseUtils.Converter
import com.manpreet.note.repository.roomdatabase.entity.NoteTable


//ToDoDatabase
@Database(entities = [(NoteTable::class)] ,version = 1 , exportSchema = false)
@TypeConverters(Converter::class )
abstract class NoteDatabase: RoomDatabase(){
    abstract fun getNoteDao (): NoteDao

    companion object {
        @Volatile
        private var INSTANCE : NoteDatabase? =null

        fun getDataBase(context: Context) : NoteDatabase {
            val tempInstance = INSTANCE
            if (tempInstance!=null ){
                return tempInstance
            }
            synchronized(this){
                val createdInstance= Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java,"NoteTable").build()
                  INSTANCE = createdInstance
                return createdInstance}
        }
    }
}

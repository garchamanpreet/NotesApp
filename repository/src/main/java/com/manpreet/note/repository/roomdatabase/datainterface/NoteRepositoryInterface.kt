package com.manpreet.note.repository.roomdatabase.datainterface

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.manpreet.note.repository.roomdatabase.dao.NoteDao
import com.manpreet.note.repository.roomdatabase.database.NoteDatabase
import com.manpreet.note.repository.roomdatabase.entity.NoteTable

/**
 * Class created to abstract the way how underneath data source
 * For now it is only local database.
 * ViewModel directly talks to this class not DB directly*/
class NoteRepositoryInterface (private val context: Context){
    private val TAG: String = "NoteDataBaseRepo"
    private fun getDataBase () : NoteDatabase? {
        return NoteDatabase.getDataBase(context)
    }
    private val noteDao: NoteDao = getDataBase()?.getNoteDao()!!
    suspend fun addNote(notesContent: NoteTable){
        Log.d(TAG, "addNote: ")
        noteDao.insertData(notesContent)
        
    }
    fun getAllNotes(): LiveData<List<NoteTable>> {
        Log.d(TAG, "getAllNotes: ")
     return noteDao.getAllData()
    }
    
   suspend fun deleteAllNotes(){
        Log.d(TAG, "deleteAllNote: ")
        noteDao.deleteAllNotes()
    }
}
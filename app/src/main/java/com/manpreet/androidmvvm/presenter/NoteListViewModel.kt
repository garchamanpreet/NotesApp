package com.manpreet.androidmvvm.presenter

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manpreet.note.repository.roomdatabase.datainterface.NoteRepositoryInterface
import com.manpreet.note.repository.roomdatabase.dao.NoteDao
import com.manpreet.note.repository.roomdatabase.entity.NoteTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Business logic to update the UI by surviving configuration changes
 * */
class NoteListViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG: String = "AddNoteViewViewModel"
    private lateinit var dataBaseRepo : NoteRepositoryInterface
    private lateinit var listOfNotes: LiveData<List<NoteTable>>

    //When ViewModel comes to existence it should get the updated data
    init{
        Log.d(TAG, "Init viewModel: ")
        viewModelScope.launch {
            dataBaseRepo= NoteRepositoryInterface(application.applicationContext)
            listOfNotes = dataBaseRepo.getAllNotes()
        }
    }

    fun addNote(noteTable: NoteTable){
        Log.d(TAG, "addNote:")
        viewModelScope.launch(Dispatchers.IO ) {
            dataBaseRepo.addNote(noteTable)
        }
    }
    
    fun deleteAllNotes(){
        viewModelScope.launch(Dispatchers.IO) {
            dataBaseRepo.deleteAllNotes()
        }
        
    }
    
    fun getListOfNotes(): LiveData<List<NoteTable>>{
        return listOfNotes
    }
    
}
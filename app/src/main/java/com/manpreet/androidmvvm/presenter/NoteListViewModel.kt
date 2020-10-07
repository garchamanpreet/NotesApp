package com.manpreet.androidmvvm.presenter

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manpreet.note.repository.roomdatabase.datainterface.NoteRepositoryInterface
import com.manpreet.note.repository.roomdatabase.entity.NoteTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Business logic to update the UI by surviving configuration changes
 * */
class NoteListViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG: String = "AddNoteViewViewModel"
    private lateinit var dataBaseRepo : NoteRepositoryInterface
    private lateinit var listOfNotes: LiveData<List<NoteTable>>
    var isDataBaseEmpty : MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    init{
        Log.d(TAG, "Init viewModel: ")
        viewModelScope.launch {
            dataBaseRepo= NoteRepositoryInterface(application.applicationContext)
            listOfNotes = dataBaseRepo.getAllNotes()
            when (listOfNotes.value.isNullOrEmpty()) {
                true-> {  isDataBaseEmpty.value =true }
                false -> {isDataBaseEmpty.value = false}
            }
        }
    }

    fun addNote(noteTable: NoteTable) {
        Log.d(TAG, "addNote:")
        viewModelScope.launch(Dispatchers.IO) {
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
<<<<<<< HEAD

=======
>>>>>>> featureUpdates
    fun updateNote(noteTable: NoteTable){
        viewModelScope.launch(Dispatchers.IO) {
            dataBaseRepo.updateNote(noteTable)
        }
<<<<<<< HEAD

=======
>>>>>>> featureUpdates
    }
    fun deleteThisNote(idPrimary: Int){
        viewModelScope.launch(Dispatchers.IO) {
            dataBaseRepo.deleteThisNote(idPrimary)
        }
    }
<<<<<<< HEAD
}
=======

}
>>>>>>> featureUpdates

package com.manpreet.androidmvvm.presenter

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.manpreet.androidmvvm.R
import com.manpreet.note.repository.roomdatabase.entity.NoteTable
import com.manpreet.note.repository.roomdatabase.entity.Priority
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * The data managed and retrieved from this viewModel will be updating two fragments
 * 1. AddToListFragment
 * 2. UpdateListFragment
 * */
class SharedViewModel(application: Application) :AndroidViewModel(application) {
    private val TAG ="SharedViewModel"
    // Listener to colour code the spinner
   val itemSelectedListener: AdapterView.OnItemSelectedListener =object : AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(parent: AdapterView<*>?) {}
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            Log.d(TAG, "onItemSelected: ")
            when (position){
                0->{
                    (parent?.getChildAt(0) as TextView)
                        .setTextColor(
                            ContextCompat
                                .getColor(application,R.color.priority_high))
                }
                1->{
                    (parent?.getChildAt(0) as TextView)
                        .setTextColor(
                            ContextCompat
                                .getColor(application,R.color.priority_medium))
                }
                2->{
                    (parent?.getChildAt(0) as TextView)
                        .setTextColor(
                            ContextCompat
                                .getColor(application,R.color.priority_low))
                }
               
            }
        }
    }
    
    fun parsePriorityToEnum(priority: String): Priority {
        return  when(priority){
            "High Priority" -> Priority.HIGH_PRIORITY
            "Medium Priority" -> Priority.MEDIUM_PRIORITY
            "Low Priority" -> Priority.LOW_PRIORITY
    
            else ->  Priority.LOW_PRIORITY
        }
    }
    
     fun validateData(title: String, description: String ): Boolean{
        return !(title.isEmpty() || description.isEmpty())
        
    }

    fun selectThePosition(priority: Priority):Int{
        return when(priority){
            Priority.HIGH_PRIORITY -> 0
            Priority.MEDIUM_PRIORITY -> 1
            Priority.LOW_PRIORITY-> 2
        }
    }
<<<<<<< HEAD

=======
>>>>>>> featureUpdates
}
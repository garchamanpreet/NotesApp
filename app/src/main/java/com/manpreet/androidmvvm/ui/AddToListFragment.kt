package com.manpreet.androidmvvm.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.manpreet.androidmvvm.R
import com.manpreet.androidmvvm.presenter.NoteListViewModel
import com.manpreet.androidmvvm.presenter.SharedViewModel
import com.manpreet.note.repository.roomdatabase.entity.NoteTable
import kotlinx.android.synthetic.main.fragment_add_to_list.*
import kotlinx.android.synthetic.main.fragment_add_to_list.view.*

class AddToListFragment : Fragment() {
    private val TAG ="AddToListFragment"
    //Kotlin way of initialization
    private val addNoteViewViewModel : NoteListViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: ")
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_to_list, container, false)
        setHasOptionsMenu(true)
        view.priority_spinner.onItemSelectedListener = sharedViewModel.itemSelectedListener
        return view
    }
    //Inflating the menu option
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.d(TAG, "onCreateOptionsMenu: ")
        inflater.inflate(R.menu.save_notes_for_add_fragment,menu)
    }
    // To handle click event on choosing save option
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "onOptionsItemSelected: ")
        if (item.itemId == R.id.id_save_note) {
            addNoteToDb()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun addNoteToDb() {
        Log.d(TAG, "addNoteToDb: ")
        //getData and getReferences directly via kotlin extension lib, no use of findViewById()
        val noteTitle = note_title_editView.text.toString()
        val noteDescription = note_description.text.toString()
        
        //validate if noteTitle or description is not empty
        val validate = sharedViewModel.validateData(noteTitle,noteDescription)
        val priority = priority_spinner.selectedItem.toString()
        val parsePriority = sharedViewModel.parsePriorityToEnum(priority)
        when (validate){
            true -> {
                val note = NoteTable(0, noteTitle, parsePriority, noteDescription)
                addNoteViewViewModel.addNote(note)
                Toast.makeText(this.context, "Note added", Toast.LENGTH_SHORT).show()
                //navigate back to list fragment
                findNavController().navigate(R.id.action_addToListFragment_to_noteListFragment)
            }
            else -> Toast.makeText(this.context,"Add title or description",Toast.LENGTH_SHORT).show()
        }
    }
}
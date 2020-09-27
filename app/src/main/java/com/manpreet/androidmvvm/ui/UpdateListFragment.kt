package com.manpreet.androidmvvm.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.manpreet.androidmvvm.R
import com.manpreet.note.repository.roomdatabase.entity.Priority
import kotlinx.android.synthetic.main.fragment_update_list.view.*

class UpdateListFragment : Fragment() {
    val arguments: UpdateListFragmentArgs by navArgs()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_update_list, container, false)
        //retrieve the data send from add fragment
        view.update_fragment_note_title.setText(arguments.updateListDataFromNoteListFragment.title)
        view.update_fragment_note_description.setText(arguments.updateListDataFromNoteListFragment.description)
        
        view.update_fragment_priority_spinner.setSelection(selectThePosition(arguments.updateListDataFromNoteListFragment.priority))
        setHasOptionsMenu(true)
        return view
        
    }
    
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_notes_for_add_fragment,menu)
    }
    
    private fun selectThePosition(priority: Priority):Int{
        return when(priority){
            Priority.HIGH_PRIORITY -> 0
            Priority.MEDIUM_PRIORITY -> 1
            Priority.LOW_PRIORITY-> 2
        }
    }
    
    
}
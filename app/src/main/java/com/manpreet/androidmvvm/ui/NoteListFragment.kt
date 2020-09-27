package com.manpreet.androidmvvm.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.manpreet.androidmvvm.R
import com.manpreet.androidmvvm.adapter.NoteListAdapter
import com.manpreet.androidmvvm.presenter.NoteListViewModel
import kotlinx.android.synthetic.main.fragment_note_list.*
import kotlinx.android.synthetic.main.fragment_note_list.view.*


class NoteListFragment : Fragment() {

private val adapter: NoteListAdapter by lazy{NoteListAdapter()}
    private val noteListViewModel: NoteListViewModel by viewModels()
    
    //called once the Fragment has been created in order for it to create its UI
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment and return it
        val view = inflater.inflate(R.layout.fragment_note_list, container, false)
        val recyclerView = view.note_list_recycler_view
        recyclerView.adapter= adapter
        recyclerView.layoutManager= GridLayoutManager(requireActivity(),2)
        noteListViewModel.getListOfNotes().observe(viewLifecycleOwner , Observer {
            if (it.isNotEmpty()){
                empty_inbox.visibility =  View.INVISIBLE
                no_notes_available.visibility=  View.INVISIBLE
            }
            adapter.setData(it)
        })
        
        //The actions are created already when we link different destinations
        //Adding navigation to addToListFragment
        view.add_new_notes_button.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_addToListFragment)
        }
        
        //Setting up the option menu, then call goes to onCreateOptionMenu
        setHasOptionsMenu(true)
        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_search_option_list,menu)
    }
    
    // to handle click event on choosing save option
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.id_delete_all) {
            noteListViewModel.deleteAllNotes()
            empty_inbox.visibility = View.VISIBLE
            no_notes_available.visibility= View.VISIBLE
        }
        
        return super.onOptionsItemSelected(item)
    }

}
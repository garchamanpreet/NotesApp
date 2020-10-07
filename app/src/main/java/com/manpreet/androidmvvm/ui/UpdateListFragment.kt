package com.manpreet.androidmvvm.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.manpreet.androidmvvm.R
import com.manpreet.androidmvvm.presenter.NoteListViewModel
import com.manpreet.androidmvvm.presenter.SharedViewModel
import com.manpreet.note.repository.roomdatabase.entity.NoteTable
<<<<<<< HEAD
import com.manpreet.note.repository.roomdatabase.entity.Priority
=======
>>>>>>> featureUpdates
import kotlinx.android.synthetic.main.fragment_update_list.*
import kotlinx.android.synthetic.main.fragment_update_list.view.*
import org.w3c.dom.Text

class UpdateListFragment : Fragment() {
    private val arguments: UpdateListFragmentArgs by navArgs()
    private val sharedViewModel : SharedViewModel by viewModels()
    private val noteListViewModel: NoteListViewModel by viewModels()
<<<<<<< HEAD

=======
    private lateinit var currentNote: NoteTable
>>>>>>> featureUpdates
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_update_list, container, false)
        //retrieve the data send from add fragment
        retrievePassedData(view)
        setHasOptionsMenu(true)
        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment,menu)
<<<<<<< HEAD
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.id_save_note -> {
                updateTheNote()
            }
            R.id.id_delete_note->{
                deleteNote()
            }
=======
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.id_save_note -> {
                updateTheNote()
            }
            R.id.id_delete_note->{
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    internal fun deleteNote() {
        confirmDelete(requireContext(),getCurrentNote())
    }

    internal fun updateTheNote() {
        //id plays important role , it must be same as passed content , otherwise database wont be updated
        noteListViewModel.updateNote(getCurrentNote())
        findNavController().navigate(R.id.action_updateListFragment_to_noteListFragment)
    }
    internal fun retrievePassedData(view: View){
        view.update_fragment_note_title.setText(arguments.updateListDataFromNoteListFragment.title)
        view.update_fragment_note_description.setText(arguments.updateListDataFromNoteListFragment.description)
        view.update_fragment_priority_spinner.setSelection(
            sharedViewModel.selectThePosition(arguments.updateListDataFromNoteListFragment.priority))
    }

    internal fun getCurrentNote() =  NoteTable(arguments.updateListDataFromNoteListFragment.id,
        update_fragment_note_title.text.toString(),
        sharedViewModel.parsePriorityToEnum(update_fragment_priority_spinner.selectedItem.toString()),
        update_fragment_note_description.text.toString()
    )

    fun confirmDelete(context: Context, note: NoteTable) {
        val dialogBox= AlertDialog.Builder(context)
        dialogBox.apply {
            setNegativeButton("DECLINE") { dialog, which -> }
            setPositiveButton("CONFIRM") { dialog, which ->
                noteListViewModel.deleteThisNote(note.id)
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateListFragment_to_noteListFragment)
            }
            setMessage("CONFIRM DELETE")
            setTitle("Are you sure to delete note\n${note.title}?")
            create().show()

>>>>>>> featureUpdates
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {
        noteListViewModel.deleteThisNote(arguments.updateListDataFromNoteListFragment.id)
        findNavController().navigate(R.id.action_updateListFragment_to_noteListFragment)

    }

    private fun updateTheNote() {
        //id plays important role , it must be same as passed content , otherwise database wont be updated
        val noteTable = NoteTable(arguments.updateListDataFromNoteListFragment.id,
            update_fragment_note_title.text.toString(),
            sharedViewModel.parsePriorityToEnum(update_fragment_priority_spinner.selectedItem.toString()),
            update_fragment_note_description.text.toString()
        )
        noteListViewModel.updateNote(noteTable)
        findNavController().navigate(R.id.action_updateListFragment_to_noteListFragment)
    }
    private fun retrievePassedData(view: View){
        view.update_fragment_note_title.setText(arguments.updateListDataFromNoteListFragment.title)
        view.update_fragment_note_description.setText(arguments.updateListDataFromNoteListFragment.description)
        view.update_fragment_priority_spinner.setSelection(
            sharedViewModel.selectThePosition(arguments.updateListDataFromNoteListFragment.priority))
    }
}
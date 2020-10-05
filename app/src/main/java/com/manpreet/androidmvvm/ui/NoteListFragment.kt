package com.manpreet.androidmvvm.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.*
import com.google.android.material.snackbar.Snackbar
import com.manpreet.androidmvvm.R
import com.manpreet.androidmvvm.adapter.NoteListAdapter
import com.manpreet.androidmvvm.adapter.SwipeToDelete
import com.manpreet.androidmvvm.databinding.FragmentNoteListBinding
import com.manpreet.androidmvvm.presenter.NoteListViewModel
import com.manpreet.note.repository.roomdatabase.entity.NoteTable
import kotlinx.android.synthetic.main.fragment_note_list.*

class NoteListFragment: Fragment() {
    private val adapter: NoteListAdapter by lazy{NoteListAdapter()}
    private val noteListViewModel: NoteListViewModel by viewModels()
    private var dataBinding: FragmentNoteListBinding? = null
    //called once the Fragment has been created in order for it to create its UI
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout with data binding lib for this fragment and return it
        val dataBinding = FragmentNoteListBinding.inflate( inflater, container, false)
        dataBinding.lifecycleOwner = this
        dataBinding.noteViewModel = noteListViewModel
       val noteRecyclerView= setUpDataBinding(dataBinding)
        setUpLiveDataObservation(noteListViewModel)
        setUpSwipeToDeleteMethod(noteRecyclerView)
        setHasOptionsMenu(true)
        return dataBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dataBinding=null
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_fragment_option_list,menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.id_delete_all -> confirmDeleteAll(requireContext())
        }
        return super.onOptionsItemSelected(item)
    }
    private fun setUpDataBinding(dataBinding: FragmentNoteListBinding):RecyclerView {
        val recyclerView = dataBinding.noteListRecyclerView
        recyclerView.adapter= adapter
        recyclerView.layoutManager=  StaggeredGridLayoutManager(2,RecyclerView.VERTICAL)
        return recyclerView
    }
    private fun setUpLiveDataObservation(noteListViewModel: NoteListViewModel) {
        noteListViewModel.getListOfNotes().observe(viewLifecycleOwner , Observer {
            if (it.isNotEmpty()){
                empty_inbox.visibility =  View.INVISIBLE
                no_notes_available.visibility=  View.INVISIBLE
            }
            adapter.setData(it)
        })
    }
    private fun setUpSwipeToDeleteMethod(recyclerView: RecyclerView) {
        val deleteCallback= object: SwipeToDelete(requireContext(),0, ItemTouchHelper.LEFT){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                super.onSwiped(viewHolder, direction)
                noteListViewModel.deleteThisNote(adapter.noteList[viewHolder.adapterPosition].id)
                if (adapter.noteList.isEmpty()){
                    empty_inbox.visibility = View.VISIBLE
                    no_notes_available.visibility= View.VISIBLE
                }

                adapter.notifyItemChanged(viewHolder.adapterPosition)

                undoDelete(viewHolder.itemView,
                    adapter.noteList[viewHolder.adapterPosition],
                    viewHolder.adapterPosition )
            }
        }
        //attaching callback to recyclerView
        ItemTouchHelper(deleteCallback).attachToRecyclerView(recyclerView)
    }
    fun confirmDeleteAll(context: Context) {
        val dialogBox= AlertDialog.Builder(context)
        dialogBox.apply {
            setNegativeButton("Decline") { dialog, which -> }
            setPositiveButton("Accept") { dialog, which ->
                noteListViewModel.deleteAllNotes()
                empty_inbox.visibility = View.VISIBLE
                no_notes_available.visibility= View.VISIBLE
                Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show()
            }
            setMessage("CONFIRM DELETE")
            setTitle("Are you sure to delete all notes?")
            create().show()
        }
    }

    fun undoDelete(view:View, deletedNote: NoteTable, position: Int){
        val snackbar= Snackbar.make(view, "Deleted ${deletedNote.title}}", 3000)
        snackbar.setAction("Undo"){
            noteListViewModel.addNote(deletedNote)
            adapter.notifyItemChanged(position)
        }
        snackbar.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryColor))
        snackbar.setActionTextColor( ContextCompat.getColor(requireContext(),R.color.primaryColor) )
        snackbar.show()
    }
}
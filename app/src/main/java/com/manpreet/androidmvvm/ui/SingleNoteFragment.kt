package com.manpreet.androidmvvm.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.manpreet.androidmvvm.R
import com.manpreet.note.repository.roomdatabase.entity.Priority
import kotlinx.android.synthetic.main.detail_note_view.*
import kotlinx.android.synthetic.main.detail_note_view.view.*
import kotlinx.android.synthetic.main.fragment_update_list.view.*
import kotlinx.android.synthetic.main.note_list_layout.view.*

class SingleNoteFragment : Fragment() {
    private val arguments: SingleNoteFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      val view =inflater.inflate(R.layout.detail_note_view, container, false)
        getDetailedNote(view)
        setHasOptionsMenu(true)
        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment,menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.id_save_note -> {

            }
            R.id.id_delete_note->{

            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun getDetailedNote(view: View) {
        view.detail_note_title.text = arguments.noteListToNoteDetailFragment.title
        view.detail_note_description.text = arguments.noteListToNoteDetailFragment.description

        when (arguments.noteListToNoteDetailFragment.priority){
            Priority.HIGH_PRIORITY->
                view.detail_note_priority_indicator.setCardBackgroundColor(
                    ContextCompat.getColor( requireContext(),R.color.priority_high))
            Priority.MEDIUM_PRIORITY->
                view.detail_note_priority_indicator.setCardBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),R.color.priority_medium))
            Priority.LOW_PRIORITY->
                view.detail_note_priority_indicator.setCardBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),R.color.priority_low))
        }

    }

}
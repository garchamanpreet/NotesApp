package com.manpreet.androidmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.manpreet.androidmvvm.R
import com.manpreet.androidmvvm.ui.NoteListFragmentDirections
import com.manpreet.note.repository.roomdatabase.entity.NoteTable
import com.manpreet.note.repository.roomdatabase.entity.Priority
import kotlinx.android.synthetic.main.note_list_layout.view.*


internal class NoteListAdapter: RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {
    internal var noteList= emptyList<NoteTable>()
    //Each view holder is in charge of displaying a single item with a view(note_list_layout)
    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { }
    //when recycler view need new view holder to inflate, creates the views and store it
    //reference of note list layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val noteContainerLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_list_layout,
                parent,
                false)
        return NoteViewHolder(noteContainerLayout)
    }
    // Data binding with view
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       /* holder.itemView.apply{
            //send the data to update fragment
            note_container.setOnClickListener {
                findNavController().navigate(NoteListFragmentDirections
                    .actionNoteListFragmentToUpdateListFragment(noteList[position]))
            }
            note_container_title.text = noteList[position].title
            note_container_description.text = noteList[position].description
            when (noteList[position].priority){
                Priority.HIGH_PRIORITY->
                    note_priority_indicator.setCardBackgroundColor(
                        ContextCompat.getColor(
                            holder.itemView.context,R.color.priority_high))
                Priority.MEDIUM_PRIORITY->
                    note_priority_indicator.setCardBackgroundColor(
                        ContextCompat.getColor(
                            holder.itemView.context,R.color.priority_medium))
                Priority.LOW_PRIORITY->
                    note_priority_indicator.setCardBackgroundColor(
                        ContextCompat.getColor(
                            holder.itemView.context,R.color.priority_low))
            }
        }*/

        //send data to detail note fragment
        holder.itemView.apply{
            note_container.setOnClickListener {
                findNavController().navigate(NoteListFragmentDirections
                    .actionNoteListFragmentToSingleNoteFragment(noteList[position]))
            }
            note_container_title.text = noteList[position].title
            note_container_description.text = noteList[position].description
            when (noteList[position].priority){
                Priority.HIGH_PRIORITY->
                    note_priority_indicator.setCardBackgroundColor(
                        ContextCompat.getColor(
                            holder.itemView.context,R.color.priority_high))
                Priority.MEDIUM_PRIORITY->
                    note_priority_indicator.setCardBackgroundColor(
                        ContextCompat.getColor(
                            holder.itemView.context,R.color.priority_medium))
                Priority.LOW_PRIORITY->
                    note_priority_indicator.setCardBackgroundColor(
                        ContextCompat.getColor(
                            holder.itemView.context,R.color.priority_low))
            }
        }
    }
    //total size of data to display to scroll, screen size does not matter
    override fun getItemCount(): Int {
        return noteList.size
    }
    //This will be called from outside to provides the data updates
    fun setData(noteTable: List<NoteTable>){
        this.noteList = noteTable
        notifyDataSetChanged()
    }
}


package com.manpreet.androidmvvm.adapter

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

//Creating it as an abstract for giving directions based on current context and directions to be used as desire
abstract class SwipeToDelete(context: Context,
                              dragDirection:Int,
                             directionsToDelete:Int
) : ItemTouchHelper.SimpleCallback(dragDirection,directionsToDelete) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    }

}
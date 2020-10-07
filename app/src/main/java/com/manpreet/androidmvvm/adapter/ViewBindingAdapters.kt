package com.manpreet.androidmvvm.adapter

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.manpreet.androidmvvm.R
import com.manpreet.note.repository.roomdatabase.entity.NoteTable


class ViewBindingAdapters  {
    companion object {
        //always start you attribute with android: , otherwise compiler wont find the data source to bind
        // @JvmStatic is important to make fun static otherwise crash will happen at runtime
        // reason we are not using DataBindingUtil.setDefaultComponent
        @BindingAdapter("android:navigateToAddNotesFragment")
          @JvmStatic
          fun navigateToAddFragment(view : FloatingActionButton, navigateFlag: Boolean){
              view.setOnClickListener{
                  when (navigateFlag){
                      true-> view.findNavController().navigate(R.id.action_noteListFragment_to_addToListFragment)
                  }
              }
          }
        @BindingAdapter("android:isDataBaseEmpty")
        @JvmStatic
        fun dataBaseCheck(view: View ,data: MutableLiveData<Boolean>): Int{
            return when(data.value){
                true -> View.VISIBLE
                false-> View.INVISIBLE
                null-> View.INVISIBLE
            }

        }
    }
}

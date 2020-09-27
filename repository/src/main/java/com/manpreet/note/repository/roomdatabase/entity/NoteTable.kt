package com.manpreet.note.repository.roomdatabase.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


// Model for the Note Table  ToDodata todo_table
@Entity(tableName = "note_table")
@Parcelize
data class NoteTable (
    @PrimaryKey (autoGenerate = true)  var id :Int,
    @ColumnInfo (name = "noteTitle") var title: String,
    @ColumnInfo (name = "notePriority") var priority: Priority,
    @ColumnInfo (name = "noteDescription") var description: String
    ):Parcelable

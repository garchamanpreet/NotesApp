package com.manpreet.note.repository.roomdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.manpreet.note.repository.roomdatabase.entity.NoteTable

//Queries for Note DataBase ToDoDao

@Dao
interface NoteDao {
    @Query ("SELECT * FROM  note_table  ORDER BY id ASC ")  fun getAllData(): LiveData<List<NoteTable>>
    @Insert (onConflict = OnConflictStrategy.IGNORE) suspend fun insertData(noteTable: NoteTable)
    @Query("DELETE FROM note_table") suspend fun deleteAllNotes()
    @Update(onConflict =  OnConflictStrategy.REPLACE) suspend fun updateNotes(note : NoteTable): Int
    //@Delete suspend fun deleteThisNote(int: I) :Int

    @Query("Delete FROM note_table where id LIKE  :id")  suspend fun deleteThisNote(id : Int) :Int
}
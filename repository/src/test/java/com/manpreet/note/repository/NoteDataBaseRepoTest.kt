package com.manpreet.note.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.manpreet.note.repository.roomdatabase.datainterface.NoteDataBaseRepo
import com.manpreet.note.repository.roomdatabase.entity.NoteTable
import com.manpreet.note.repository.roomdatabase.entity.Priority
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
class NoteDataBaseRepoTest  {
    lateinit var testingCoroutineScope: CoroutineScope
    lateinit var noteDataBaseRepo: NoteDataBaseRepo
    lateinit var context: Context
    lateinit var noteTable: NoteTable
    lateinit var noteDataTestUtils: NoteDataTestUtils
    @Before
    fun setUp() {
        context= mockk<Context>()
        testingCoroutineScope = CoroutineScope(Dispatchers.IO)
        noteDataBaseRepo = mockk<NoteDataBaseRepo>()
        noteDataTestUtils = NoteDataTestUtils()
    }
    
    @After
    fun tearDown() {
    
    }
    
    
    @Test
    fun addNote() {
        testingCoroutineScope.launch {
            noteDataBaseRepo.addNote(noteDataTestUtils.getNoteTableDataList()[0])
        }
    }
    
    @Test
    fun getAllNotes() {
       
       /* every {noteDataBaseRepo.getAllNotes()}.returns(noteDataTestUtils.getNoteTableDataList()[0])
        
        testingCoroutineScope.launch {
           retrivedData= noteDataBaseRepo.getAllNotes()
        }*/
        
        
        
    }
}
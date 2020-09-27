package com.manpreet.note.repository

import com.manpreet.note.repository.roomdatabase.entity.NoteTable
import com.manpreet.note.repository.roomdatabase.entity.Priority

internal class NoteDataBaseTestUtils {
    
    private val noteTableOne =
        NoteTable(0, "testTitle_One", Priority.HIGH_PRIORITY, "testing description")
    private val noteTableTwo =
        NoteTable(0, "testTitle_Two", Priority.LOW_PRIORITY, "testing description two ")
    private val noteTableDataSet = listOf<NoteTable>(noteTableOne, noteTableTwo)
    
    fun getNoteTableDataList(): List<NoteTable> = noteTableDataSet
    
    
}
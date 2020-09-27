package com.manpreet.note.repository

import android.content.Context
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.manpreet.note.repository.roomdatabase.dao.NoteDao
import com.manpreet.note.repository.roomdatabase.database.NoteDatabase
import com.manpreet.note.repository.roomdatabase.entity.NoteTable

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteDataBaseTest  {
    private lateinit var dataBase : NoteDatabase
    private lateinit var noteDao: NoteDao
    private lateinit var context: Context
    private lateinit var testingCoroutineScope: CoroutineScope
    private lateinit var noteDataTestUtils: NoteDataBaseTestUtils
    @Before
    fun initTestEnvSetUp(){
        context= InstrumentationRegistry.getInstrumentation().targetContext
        noteDataTestUtils = NoteDataBaseTestUtils()
        testingCoroutineScope = CoroutineScope(Dispatchers.IO)
        dataBase = Room.inMemoryDatabaseBuilder(context,NoteDatabase::class.java).build()
        noteDao= dataBase.getNoteDao()
    }
    @Test
    fun testDataBaseCreation (){
        Assert.assertNotNull("DataBase created ",dataBase)
    }
    
    @Test
    fun test_getAllData_returnsInsertedData()= runBlocking{
        
            noteDao.insertData(noteDataTestUtils.getNoteTableDataList()[0])
            noteDao.insertData(noteDataTestUtils.getNoteTableDataList()[1])
        
            val data =noteDao.getAllData()
            // properties of table must not null
            Assert.assertNotNull("Returned Data not null ",data )
        }
    
     
}

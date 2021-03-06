package com.ansela.notesapp.data.repository

import androidx.lifecycle.LiveData
import com.ansela.notesapp.data.model.NoteDao
import com.ansela.notesapp.data.model.NoteData
import com.ansela.notesapp.data.model.NoteDatabase

class NoteRepository (private val noteDao: NoteDao) {

    val getAllData : LiveData<List<NoteData>> = noteDao.getAllData()
    val sortByHighPriority : LiveData<List<NoteData>> = noteDao.sortByHighPriority()
    val sortByLowPriority : LiveData<List<NoteData>> = noteDao.sortByLowPriority()

    fun insertData(noteData : NoteData){
        noteDao.insertData(noteData)
    }

    fun updateData(noteData: NoteData){
        noteDao.updateData(noteData)
    }

    fun deleteData(noteData: NoteData){
        noteDao.deleteData(noteData)
    }

    fun deleteAllData(){
        noteDao.deleteAllData()
    }

    fun searchData(searchQuery : String) : LiveData<List<NoteData>>{
        return noteDao.searchDatabase(searchQuery)
    }

}
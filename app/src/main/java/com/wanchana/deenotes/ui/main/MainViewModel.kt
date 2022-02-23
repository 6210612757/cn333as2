package com.wanchana.deenotes.ui.main

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import com.wanchana.deenotes.models.Noted

class MainViewModel(val sharedPreferences: SharedPreferences) : ViewModel() {

    lateinit var list: Noted

    lateinit var onListAdded: () -> Unit
    lateinit var onListRemoved: () -> Unit
    var whereRemoved: Int = -1

    val lists: MutableList<Noted> by lazy {
        retrieveLists()
    }

    // key: list-name, value: hashset (list of items)
    private fun retrieveLists(): MutableList<Noted> {
        
        val sharedPreferencesContents = sharedPreferences.all
        val noteLists = ArrayList<Noted>()

        for (noteList in sharedPreferencesContents) {

            val note = Noted(noteList.key, noteList.value as String)
            noteLists.add(note)
        }

        return noteLists
    }

    fun createList(list: Noted) {
        val editor = sharedPreferences.edit()
        val text: String = list.content
        editor.putString(list.name, text)
        editor.apply()
        lists.add(list)
        onListAdded.invoke()

    }

    fun saveList(list: Noted) {
        val editor = sharedPreferences.edit()
        val text: String = list.content
        editor.putString(list.name, text)
        editor.apply()
        Log.d(ContentValues.TAG, list.content)

    }

    fun updateList(list: Noted) {
        val editor = sharedPreferences.edit()
        val text: String = list.content
        editor.putString(list.name, text)
        editor.apply()
        Log.d(ContentValues.TAG, list.content)
        refreshLists()
    }
    fun removeList(list: Noted){
        val index = lists.indexOf(list)
        whereRemoved = index
        lists.remove(list)
        onListRemoved.invoke()

        val editor = sharedPreferences.edit()
        editor.remove(list.name)
        editor.apply()
//        refreshLists()
    }

    fun findList(key: String): Boolean{
        return sharedPreferences.contains(key)
    }

    private fun refreshLists() {
        lists.clear()
        lists.addAll(retrieveLists())
    }

}


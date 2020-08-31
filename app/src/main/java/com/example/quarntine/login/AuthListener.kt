package com.example.quarntine.login

interface AuthListener {
    fun onStart()
    fun onSuccess()
    fun onFailure(message: String)

}

interface FirebaseManager {
    fun saveData()
    fun getData()
    fun upLoadData()
    fun deleteData()

}

class FirebaseRepositry : FirebaseManager {
    override fun saveData() {

    }

    override fun getData() {
    }

    override fun upLoadData() {
    }

    override fun deleteData() {
    }

}
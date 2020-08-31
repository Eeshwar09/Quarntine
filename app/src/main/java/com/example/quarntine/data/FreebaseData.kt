package com.example.quarntine.data

import com.example.quarntine.login.FirebaseManager
import com.example.quarntine.model.Movies
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FreebaseData : FirebaseManager {
    val TAG = "FIREBASE_REPOSITORY"
    private val database = FirebaseDatabase.getInstance()
    val mMessageReference = database.getReference("message")
    val heroid = mMessageReference.push().key
    val upload = Movies(heroid!!, "prasad", "gh")
    private lateinit var onUser: ((Movies?) -> Unit)
    private lateinit var userId: String
    private val valueEventListener = object : ValueEventListener {
        override fun onCancelled(p0: DatabaseError) {
            onUser(null)
            close()

        }

        override fun onDataChange(p0: DataSnapshot) {
            val user = p0.child(heroid!!).getValue(Movies::class.java)
            onUser
            close()


        }
    }

    private fun close() {
        mMessageReference.removeEventListener(valueEventListener)
    }

    override fun saveData() {
        mMessageReference.child(heroid!!).setValue(upload).addOnSuccessListener {

        }


    }

    override fun getData() {


    }

    override fun upLoadData() {
    }

    override fun deleteData() {
    }
}
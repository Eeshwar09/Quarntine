package com.example.quarntine.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quarntine.model.Movies
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@Suppress("TYPEALIAS_EXPANSION_DEPRECATION")
class MainActivityViewModel : ViewModel() {

    private var _spaceList = MutableLiveData<MutableList<Movies>>()


    val db = FirebaseDatabase.getInstance()
    private val mMessageReference = db.getReference("message")
    private val heroid = mMessageReference.push().key

    fun addData(): LiveData<MutableList<Movies>> {
        val postListner =
            mMessageReference.child(heroid!!).addValueEventListener(object : ValueEventListener {
                @SuppressLint("LogNotTimber")
                override fun onCancelled(p0: DatabaseError) {
                    Log.d("", "Failed to read value.", p0.toException())

                }

                override fun onDataChange(p0: DataSnapshot) {
                    Log.d("", "Success to read value.")

                    val posts = ArrayList<Movies>()
                    if (p0.exists()) {
                        posts.clear()
                        for (snapshot in p0.children) {
                            val post = snapshot.getValue(Movies::class.java)
                            posts.add(post!!)
                            _spaceList.value = posts

                        }
                    }
                }

            })
        mMessageReference.addValueEventListener(postListner)
        return _spaceList
    }
}
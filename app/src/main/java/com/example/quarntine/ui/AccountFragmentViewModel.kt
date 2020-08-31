package com.example.quarntine.ui

import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quarntine.login.LoginActivity
import com.example.quarntine.login.UserRepository
import com.example.quarntine.model.Movies
import com.google.firebase.database.FirebaseDatabase

@Suppress("DEPRECATION")
class AccountFragmentViewModel(val repository: UserRepository) : ViewModel() {
    private var _spaceList = MutableLiveData<MutableList<Movies>>()
    val db = FirebaseDatabase.getInstance()
    private val mMessageReference = db.getReference("message")
    private val heroid = mMessageReference.push().key
    fun savedata(t: String, p: String, q: String) {
        val movies = Movies(t, p, q)
        mMessageReference.child(heroid.toString()).setValue(movies)


    }

    val user by lazy {
        repository.currentUser()
    }

    fun logout(view: View) {
        repository.logout()
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)

        }
    }




    }
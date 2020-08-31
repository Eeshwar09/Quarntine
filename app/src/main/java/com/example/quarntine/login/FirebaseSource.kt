package com.example.quarntine.login

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable
import timber.log.Timber

class FirebaseSource {
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun login(email:String,password:String) = Completable.create { emitter ->
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
            if(!emitter.isDisposed){
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }


    }
    fun register(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }
    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser
    companion object {
        private var appDatabase: FirebaseSource? = null

        fun getInstance(): FirebaseSource {
            if (appDatabase == null)
                appDatabase =
                   FirebaseSource()
            return appDatabase!!
        }
    }
     fun sendPasswordResetEmail(email: String) = Completable.create {emitter ->
         firebaseAuth.sendPasswordResetEmail(email)
             .addOnCompleteListener {task ->

               if (!emitter.isDisposed){
                   if (task.isSuccessful)
                       emitter.onComplete()

                       else
                       emitter.onError(task.exception!!)
               }

             }

     }

}
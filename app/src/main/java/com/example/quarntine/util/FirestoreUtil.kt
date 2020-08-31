package com.example.quarntine.util

import android.content.Context
import android.util.Log
import com.example.quarntine.model.Movies
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.xwray.groupie.kotlinandroidextensions.Item
import java.lang.NullPointerException
import java.util.*

object FirestoreUtil {
    private val firestoreInstance: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
    private val currentUserDocRef: DocumentReference
        get() = firestoreInstance.document(
            "users/${FirebaseAuth.getInstance().currentUser?.uid ?: throw NullPointerException("UID is null")}")

    fun initCurrentuserFirsttime(onComplete:()-> Unit){
        currentUserDocRef.get().addOnSuccessListener {
            if(!it.exists())
            {
                val newUser = Movies(FirebaseAuth.getInstance().currentUser?.displayName?:"","","")
                currentUserDocRef.set(newUser).addOnSuccessListener {
                    onComplete()
                }
            }
            else{
                onComplete()
            }
        }
    }
    fun updateCurrentUser(name:String ="", bio:String ="", image:String? = null){
        val userFieldMap = mutableMapOf<String,Any>()
        if (name.isNotBlank()) userFieldMap["name"] = name
        if (bio.isNotBlank()) userFieldMap["bio"] = bio
        if (image != null) userFieldMap["image"] = image
        currentUserDocRef.update(userFieldMap)
    }
    fun getCurrentuser(onComplete: (Movies) -> Unit){
        currentUserDocRef.get()
            .addOnSuccessListener {
                (it.toObject(Movies::class.java))
            }
    }
    fun addUsersListener(context:Context,onListen: (List<Item>) -> Unit):ListenerRegistration{
        return firestoreInstance.collection("users")
            .addSnapshotListener{querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    Log.e("FIRESTORE", "Users listener error", firebaseFirestoreException)
                    return@addSnapshotListener
                }
                val items = mutableListOf<Item>()
                if (querySnapshot != null) {
                    querySnapshot.documents.forEach {

                    }
                }

            }
    }
}

object StorageUtil{
    private val storageInstance:FirebaseStorage by lazy { FirebaseStorage.getInstance() }
    private val currentUserReference:StorageReference
    get() = storageInstance.reference
        .child(FirebaseAuth.getInstance().currentUser?.uid ?: throw NullPointerException("UID is null"))
    fun uploadProfilePhoto(imageBytes:ByteArray,onSuccess:(imagepath:String) -> Unit){
        val ref = currentUserReference.child("profilePictures/${UUID.nameUUIDFromBytes(imageBytes)}")
        ref.putBytes(imageBytes)
            .addOnSuccessListener {
                onSuccess(ref.path)
            }

    }
    fun pathToReference(path:String) = storageInstance.getReference(path)
}
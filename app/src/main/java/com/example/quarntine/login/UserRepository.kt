package com.example.quarntine.login

class UserRepository(): BaseRepository() {
    private val firebase = FirebaseSource()
    fun login(email: String, password: String) = firebase.login(email, password)

    fun register(email: String, password: String) = firebase.register(email, password)

    fun currentUser() = firebase.currentUser()

    fun logout() = firebase.logout()

    fun  sendPasswordResetEmail(email: String) = firebase.sendPasswordResetEmail(email)
}
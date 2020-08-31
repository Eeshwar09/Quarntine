@file:Suppress("DEPRECATION")

package com.example.quarntine.login

import android.content.Intent
import android.util.Patterns
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class AuthViewModel(private val repository: UserRepository) : ViewModel() {
    private var mAuth: FirebaseAuth? = null
    var loginemail: String = ""
    var loginpassword: String = ""
    var signinemail: String = ""
    var signinpassword: String = ""
    var resetEmail: String = ""
    var authListener: AuthListener? = null

    private val disposables = CompositeDisposable()
    val user by lazy {
        repository.currentUser()
    }

    fun resetPassword() {
        if (resetEmail.isEmpty()) {

            authListener!!.onFailure("email is Empty")

        } else {
            authListener?.onStart()
            val disposable = repository.sendPasswordResetEmail(resetEmail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    authListener?.onSuccess()
                },
                    {
                        authListener?.onFailure(it.message!!)
                    })
            disposables.add(disposable)

        }

    }

    fun login() {

        when {
            loginemail.isEmpty() -> {
                authListener!!.onFailure("email is Empty")
                return

            }
            loginpassword.isEmpty() -> {
                authListener!!.onFailure("Password is Empty")
                return
            }
            loginpassword.length < 6 -> {
                authListener!!.onFailure("Password not less than 6 Charcters")
                return

            }
            !Patterns.EMAIL_ADDRESS.matcher(loginemail).matches() -> authListener!!.onFailure("Please enter a valid email")
            else -> {
                authListener?.onStart()
                val disposable = repository.login(loginemail, loginpassword)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        authListener?.onSuccess()
                    },
                        {
                            authListener?.onFailure(it.message!!)
                        })
                disposables.add(disposable)


            }
        }

    }

    fun signUp() {


        if (signinemail.isEmpty()) {
            authListener!!.onFailure("email is Empty")
            return

        } else if (signinpassword.isEmpty()) {
            authListener!!.onFailure("Password is Empty")
            return
        } else if (signinpassword.length < 6) {
            authListener!!.onFailure("Password not less than 6 Charcters")
            return

        } else if (!Patterns.EMAIL_ADDRESS.matcher(signinemail).matches()) {
            authListener!!.onFailure("Please enter a valid email")

        } else {
            authListener?.onStart()
            val disposable = repository.register(signinemail, signinpassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    authListener?.onSuccess()

                }, {
                    authListener?.onFailure(it.message!!)
                })
            disposables.add(disposable)
        }
    }

    fun goTOSignUp(view: View) {
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }

    }

    fun goToLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)

        }
    }

    fun goToResetPassword(view: View) {
        Intent(view.context, ResetPasswordActivity::class.java).also {
            view.context.startActivity(it)

        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
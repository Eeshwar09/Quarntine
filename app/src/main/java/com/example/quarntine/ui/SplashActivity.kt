package com.example.quarntine.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quarntine.R
import com.example.quarntine.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (FirebaseAuth.getInstance().currentUser == null) {
            startActivity<LoginActivity>()
        } else
            startActivity<MainActivity>()
        finish()
    }
}


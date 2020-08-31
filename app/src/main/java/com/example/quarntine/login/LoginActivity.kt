@file:Suppress("DEPRECATION")

package com.example.quarntine.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quarntine.R
import kotlinx.android.synthetic.main.activity_login.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.blankj.utilcode.util.ActivityUtils.startHomeActivity
import com.example.quarntine.util.toast
import com.example.quarntine.databinding.ActivityLoginBinding
import com.example.quarntine.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity(), AuthListener {
    private val homeViewModel by viewModel<AuthViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(homeViewModel::class.java)
        binding.authviewmodel = viewModel
        viewModel.authListener = this


    }

    override fun onStart() {
        toast("login STasrted")
        super.onStart()
    }

    override fun onSuccess() {
        toast("Login Success")
        startHomeActivity()
    }

    override fun onFailure(message: String) {
        toast(message)
    }

    fun startHomeActivity() =
        Intent(this, MainActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }

    fun startLoginActivity() =
        Intent(this, LoginActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }


}

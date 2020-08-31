@file:Suppress("DEPRECATION")

package com.example.quarntine.login

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import com.example.quarntine.R
import kotlinx.android.synthetic.main.activity_main2.*
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.quarntine.databinding.ActivityLoginBinding
import com.example.quarntine.databinding.ActivityMain2Binding
import com.example.quarntine.ui.MainActivity
import com.example.quarntine.util.toast
import org.koin.androidx.viewmodel.ext.android.viewModel


@Suppress("DEPRECATION")
class SignupActivity : AppCompatActivity(), AuthListener {


    private var progressBar: ProgressBar? = null
    private val homeViewModel by viewModel<AuthViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMain2Binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)
        val viewModel = ViewModelProviders.of(this).get(homeViewModel::class.java)
        binding.signviewmodel = viewModel
        viewModel.authListener = this


    }

    override fun onStart() {
        toast("log")
        super.onStart()
    }

    override fun onSuccess() {
        toast("success")
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

}

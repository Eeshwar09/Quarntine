package com.example.quarntine.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.quarntine.R
import com.example.quarntine.databinding.ActivityResetPasswordBinding
import com.example.quarntine.util.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResetPasswordActivity : AppCompatActivity(),AuthListener {


    private val homeViewModel by viewModel<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityResetPasswordBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_reset_password)
        val viewModel = ViewModelProviders.of(this).get(homeViewModel::class.java)
        binding.passwordviewmodel = viewModel
        viewModel.authListener = this

    }
    override fun onStart() {
        toast("start")
        super.onStart()
    }
    override fun onSuccess() {
        toast("verficaton mail is sent to your Email")
    }

    override fun onFailure(message: String) {
        toast(message)
    }
}

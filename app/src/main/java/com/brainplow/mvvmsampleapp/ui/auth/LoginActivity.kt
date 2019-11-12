package com.brainplow.mvvmsampleapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.brainplow.mvvmsampleapp.R
import com.brainplow.mvvmsampleapp.databinding.ActivityLoginBinding
import com.brainplow.mvvmsampleapp.util.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),AuthListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_login)
        val binding:ActivityLoginBinding = setContentView(this,R.layout.activity_login)
        val viewModel=ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewmodel=viewModel
        viewModel.authListener=this
    }

    override fun onStarted() {
        showInfoMessage("Login Started")
        progress_bar.show()
    }

    override fun onSuccess(message: LiveData<String>) {
        //showSuccessMessage("Login Success")

        message.observe(this, Observer {
            showSuccessMessage(it)
            progress_bar.hide()
        })
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        //showErrorMessage(message)
    }
}

package com.brainplow.mvvmsampleapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.brainplow.mvvmsampleapp.R
import com.brainplow.mvvmsampleapp.data.db.AppDataBase
import com.brainplow.mvvmsampleapp.data.db.entities.User
import com.brainplow.mvvmsampleapp.data.network.MyApi
import com.brainplow.mvvmsampleapp.data.repositories.UserRepository
import com.brainplow.mvvmsampleapp.databinding.ActivityLoginBinding
import com.brainplow.mvvmsampleapp.ui.home.HomeActivity
import com.brainplow.mvvmsampleapp.util.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),AuthListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_login)
        val api=MyApi()
        val db=AppDataBase(this)
        val repository=UserRepository(api,db)
        val factory=AuthViewModelFactory(repository)
        val binding:ActivityLoginBinding = setContentView(this,R.layout.activity_login)
        val viewModel=ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel=viewModel
        viewModel.authListener=this
        viewModel.getLoggedInUser().observe(this, Observer {user ->
            if(user !=null){
                Intent(this,HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)

                }
            }

        })
    }

    override fun onStarted() {
        showInfoMessage("Login Started")
        progress_bar.show()
    }

//    override fun onSuccess(message: LiveData<String>) {
//        //showSuccessMessage("Login Success")
//
//        message.observe(this, Observer {
//            showSuccessMessage(it)
//            progress_bar.hide()
//        })
//    }
    override fun onSuccess(user: User) {
        progress_bar.hide()
        //showSuccessMessage("${user.name} Login Success")
    root_layout.snackBar("${user.name} Login Success")
    }
    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackBar("$message Login Failure")
      //  showErrorMessage(message)
    }
}

package com.brainplow.mvvmsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.brainplow.mvvmsampleapp.data.repositories.UserRepository

class AuthViewModel: ViewModel() {
    var email:String?=null
    var pwd:String?=null
    var authListener:AuthListener?=null

    fun onLoginButtonClick(view: View?){
        authListener?.onStarted()
        if(email.isNullOrEmpty()|| pwd.isNullOrEmpty()){
            //error
            authListener?.onFailure("Invalid Credentials")
            return
        }
        //success
        val loginResponse= UserRepository().userLogin(email!!,pwd!!)
        authListener?.onSuccess(loginResponse)
    }
}
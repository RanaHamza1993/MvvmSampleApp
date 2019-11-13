package com.brainplow.mvvmsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.brainplow.mvvmsampleapp.data.repositories.UserRepository
import com.brainplow.mvvmsampleapp.util.ApiException
import com.brainplow.mvvmsampleapp.util.Couroutines

class AuthViewModel(
    private val repository: UserRepository
): ViewModel() {
    var email:String?=null
    var pwd:String?=null
    var authListener:AuthListener?=null

    fun getLoggedInUser()=repository.getUser()
    fun onLoginButtonClick(view: View?){
        authListener?.onStarted()
        if(email.isNullOrEmpty()|| pwd.isNullOrEmpty()){
            //error
            authListener?.onFailure("Invalid Credentials")
            return
        }
        //success
//        val loginResponse= UserRepository().userLogin(email!!,pwd!!)
//        authListener?.onSuccess(loginResponse)
        Couroutines.main{
            try {
                val response = repository.userLogin(email!!, pwd!!)
                response.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(response.message!!)
            }catch (e:ApiException){
                authListener?.onFailure(e.message!!)
            }
        }
    }
}
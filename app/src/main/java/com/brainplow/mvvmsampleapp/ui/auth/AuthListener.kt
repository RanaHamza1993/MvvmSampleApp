package com.brainplow.mvvmsampleapp.ui.auth

import androidx.lifecycle.LiveData

interface AuthListener {

    fun onStarted()
    fun onSuccess(message: LiveData<String>)
    fun onFailure(message:String)
}
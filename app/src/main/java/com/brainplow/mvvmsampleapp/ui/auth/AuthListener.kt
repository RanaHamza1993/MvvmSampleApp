package com.brainplow.mvvmsampleapp.ui.auth

import androidx.lifecycle.LiveData
import com.brainplow.mvvmsampleapp.data.db.entities.User

interface AuthListener {

    fun onStarted()
    //fun onSuccess(message: LiveData<String>)
    fun onSuccess(user: User)
    fun onFailure(message:String)
}
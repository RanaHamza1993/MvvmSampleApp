package com.brainplow.mvvmsampleapp.data.network.responses

import com.brainplow.mvvmsampleapp.data.db.entities.User

data class AuthResponse (
    val isSuccessfull:Boolean?,
    val message:String?,
    val user:User?
)
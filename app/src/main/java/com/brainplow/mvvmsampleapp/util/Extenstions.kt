package com.brainplow.mvvmsampleapp.util

import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import es.dmoral.toasty.Toasty

fun Context.showSuccessMessage(message: String, duration:Int= Toast.LENGTH_SHORT) {
    try {
        Toasty.success(this, message, duration, true).show()

    }catch (e: WindowManager.BadTokenException){}
}

fun Context.showInfoMessage(message: String, duration:Int= Toast.LENGTH_SHORT) {
    try{
        Toasty.info(this, message, duration, true).show()
    }catch (e: WindowManager.BadTokenException){}
}

fun Context.showErrorMessage(message: String, duration:Int= Toast.LENGTH_SHORT) {
    try{
        Toasty.error(this, message, duration, true).show()
    }catch (e: WindowManager.BadTokenException){}
}

fun View.show(){
    visibility=View.VISIBLE
}
fun View.hide(){
    visibility=View.GONE
}

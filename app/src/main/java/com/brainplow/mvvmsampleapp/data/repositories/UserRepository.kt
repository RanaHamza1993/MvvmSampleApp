package com.brainplow.mvvmsampleapp.data.repositories

import com.brainplow.mvvmsampleapp.data.db.AppDataBase
import com.brainplow.mvvmsampleapp.data.db.entities.User
import com.brainplow.mvvmsampleapp.data.network.MyApi
import com.brainplow.mvvmsampleapp.data.network.SafeApiRequest
import com.brainplow.mvvmsampleapp.data.network.responses.AuthResponse
import com.brainplow.mvvmsampleapp.util.ApiException
import retrofit2.Response

class UserRepository(
    private val api:MyApi,
    private val db:AppDataBase
):SafeApiRequest() {
    suspend fun userLogin(email:String, password:String):AuthResponse{
//        val loginResponse=MutableLiveData<String>()
//        MyApi().userLogin(email,password)
//            .enqueue(object: Callback<ResponseBody> {
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                    loginResponse.value=t.message
//                }
//
//                override fun onResponse(
//                    call: Call<ResponseBody>,
//                    response: Response<ResponseBody>
//                ) {
//                    if(response.isSuccessful){
//                        loginResponse.value=response.body()?.string()
//                    }else
//                        loginResponse.value=response.errorBody()?.string()
//                }
//
//            })
        return apiRequest{MyApi().userLogin(email,password)}
    }
    suspend fun saveUser(user: User)=db.getUserDao().upsert(user)
    fun getUser()=db.getUserDao().getUser()
}
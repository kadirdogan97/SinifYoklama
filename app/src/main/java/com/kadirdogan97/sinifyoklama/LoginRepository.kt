package com.kadirdogan97.sinifyoklama

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class LoginRepository {

    val yoklamaServiceProvider = YoklamaServiceProvider()

    fun userLogin(username: String, password: String) : LiveData<Login> {
        val loginResponse = MutableLiveData<Login>()

        yoklamaServiceProvider.yoklamaService.userLogin(username,password)
            .enqueue(object: Callback<Login>{
                override fun onFailure(call: Call<Login>, t: Throwable) {
                    val tempLogin = Login(false,t.message)
                    loginResponse.value = tempLogin
                }

                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    loginResponse.value = response.body()

                }

            })

        return loginResponse
    }
}
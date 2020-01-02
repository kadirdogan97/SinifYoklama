package com.kadirdogan97.sinifyoklama.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kadirdogan97.sinifyoklama.network.model.Login
import com.kadirdogan97.sinifyoklama.network.model.LoginT
import com.kadirdogan97.sinifyoklama.network.model.Student
import com.kadirdogan97.sinifyoklama.network.model.Teacher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginRepository {

    val yoklamaServiceProvider = YoklamaServiceProvider()

    fun userLogin(username: String, password: String) : LiveData<Login> {
        val loginResponse = MutableLiveData<Login>()
        yoklamaServiceProvider.yoklamaService.userLogin(username,password)
            .enqueue(object: Callback<Login>{
                override fun onFailure(call: Call<Login>, t: Throwable) {
                    val tempLogin = Login(
                        false,
                        t.message,
                        Student(
                            1,
                            "1",
                            "1",
                            "1",
                            "1",
                            "1"
                        )
                    )
                    loginResponse.value = tempLogin
                }

                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    loginResponse.value = response.body()

                }

            })

        return loginResponse
    }

    fun userLoginT(username: String, password: String) : LiveData<LoginT> {
        val loginResponse = MutableLiveData<LoginT>()
        yoklamaServiceProvider.yoklamaService.userLoginT(username,password)
            .enqueue(object: Callback<LoginT>{
                override fun onFailure(call: Call<LoginT>, t: Throwable) {
                    val tempLogin = LoginT(
                        false,
                        t.message,
                        Teacher(
                            1,
                            "1",
                            "1"
                        )
                    )
                    loginResponse.value = tempLogin
                }
                override fun onResponse(call: Call<LoginT>, response: Response<LoginT>) {
                    loginResponse.value = response.body()
                }
            })

        return loginResponse
    }
}
package com.kadirdogan97.sinifyoklama.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kadirdogan97.sinifyoklama.network.model.Discontinuity
import com.kadirdogan97.sinifyoklama.network.model.DiscontinuityService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiscontinuityRepository {

    val yoklamaServiceProvider = YoklamaServiceProvider()

    fun getDiscontinuity(ders_id: String, ogr_id: String) : LiveData<DiscontinuityService> {
        val discontinuityResponse = MutableLiveData<DiscontinuityService>()
        yoklamaServiceProvider.yoklamaService.getDiscontinuity(ders_id,ogr_id)
            .enqueue(object: Callback<DiscontinuityService> {
                override fun onFailure(call: Call<DiscontinuityService>, t: Throwable) {
                    val tempDiscontinuity = ArrayList<Discontinuity>()
                    tempDiscontinuity.add(Discontinuity(0, "0", "0", "0", "0"))
                    val tempDiscService = DiscontinuityService(false,"0","0",tempDiscontinuity)
                    discontinuityResponse.value = tempDiscService
                    Log.d("1", "test: "+t.message)
                }

                override fun onResponse(call: Call<DiscontinuityService>, response: Response<DiscontinuityService>) {
                    Log.d("1", "test: "+response.body())
                    discontinuityResponse.value = response.body()

                }

            })

        return discontinuityResponse
    }
}
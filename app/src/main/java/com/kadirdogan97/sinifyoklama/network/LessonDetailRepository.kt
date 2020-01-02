package com.kadirdogan97.sinifyoklama.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kadirdogan97.sinifyoklama.network.model.Discontinuity
import com.kadirdogan97.sinifyoklama.network.model.DiscontinuityService
import com.kadirdogan97.sinifyoklama.network.model.ModifyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LessonDetailRepository {

    val yoklamaServiceProvider = YoklamaServiceProvider()

    fun getDiscontinuity(ders_id: String, ogr_id: String) : LiveData<DiscontinuityService> {
        val discontinuityResponse = MutableLiveData<DiscontinuityService>()
        yoklamaServiceProvider.yoklamaService.getDiscontinuity(ders_id,ogr_id)
            .enqueue(object: Callback<DiscontinuityService> {
                override fun onFailure(call: Call<DiscontinuityService>, t: Throwable) {
                    val tempDiscontinuity = ArrayList<Discontinuity>()
                    tempDiscontinuity.add(Discontinuity(0, "0", "0", "0", "0","0"))
                    val tempDiscService = DiscontinuityService(false,"0","0","0",tempDiscontinuity)
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

    fun getDiscontinuityT(ders_id: String) : LiveData<DiscontinuityService> {
        val discontinuityResponse = MutableLiveData<DiscontinuityService>()
        yoklamaServiceProvider.yoklamaService.getDiscontinuityT(ders_id)
            .enqueue(object: Callback<DiscontinuityService> {
                override fun onFailure(call: Call<DiscontinuityService>, t: Throwable) {
                    val tempDiscontinuity = ArrayList<Discontinuity>()
                    tempDiscontinuity.add(Discontinuity(0, "0", "0", "0", "0","0"))
                    val tempDiscService = DiscontinuityService(false,"0","0","0",tempDiscontinuity)
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

    fun setDiscontinuity(ders_id: String, ogr_id: String) : LiveData<ModifyResponse> {
        val discontinuityResponse = MutableLiveData<ModifyResponse>()
        yoklamaServiceProvider.yoklamaService.setDiscontinuity(ders_id,ogr_id)
            .enqueue(object: Callback<ModifyResponse> {
                override fun onFailure(call: Call<ModifyResponse>, t: Throwable) {
                    val tempResponse = ModifyResponse(false,"yoklama alırken bir sıkıntı oluştu")
                    discontinuityResponse.value = tempResponse
                    Log.d("1", "test: "+t.message)
                }

                override fun onResponse(call: Call<ModifyResponse>, response: Response<ModifyResponse>) {
                    Log.d("1", "test: "+response.body())
                    discontinuityResponse.value = response.body()
                }

            })

        return discontinuityResponse
    }

    fun setBarcode(ders_id: String, barkod: String) : LiveData<ModifyResponse> {
        val barkodResponse = MutableLiveData<ModifyResponse>()
        yoklamaServiceProvider.yoklamaService.setBarcode(ders_id,barkod)
            .enqueue(object: Callback<ModifyResponse> {
                override fun onFailure(call: Call<ModifyResponse>, t: Throwable) {
                    val tempResponse = ModifyResponse(false,"barkod oluşturulurken bir sıkıntı meydana geldi")
                    barkodResponse.value = tempResponse
                    Log.d("1", "test: "+t.message)
                }

                override fun onResponse(call: Call<ModifyResponse>, response: Response<ModifyResponse>) {
                    Log.d("1", "test: "+response.body())
                    barkodResponse.value = response.body()
                }

            })

        return barkodResponse
    }

    fun closeDiscontinuity(ders_id: String) : LiveData<ModifyResponse> {
        val closeDiscontinuity = MutableLiveData<ModifyResponse>()
        yoklamaServiceProvider.yoklamaService.closeDiscontinuity(ders_id)
            .enqueue(object: Callback<ModifyResponse> {
                override fun onFailure(call: Call<ModifyResponse>, t: Throwable) {
                    val tempResponse = ModifyResponse(false,"yoklama kapatılırken bir sıkıntı meydana geldi")
                    closeDiscontinuity.value = tempResponse
                    Log.d("1", "test: "+t.message)
                }

                override fun onResponse(call: Call<ModifyResponse>, response: Response<ModifyResponse>) {
                    Log.d("1", "test: "+response.body())
                    closeDiscontinuity.value = response.body()
                }

            })

        return closeDiscontinuity
    }



}
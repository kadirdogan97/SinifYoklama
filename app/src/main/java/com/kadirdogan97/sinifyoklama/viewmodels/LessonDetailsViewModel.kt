package com.kadirdogan97.sinifyoklama.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import com.kadirdogan97.sinifyoklama.network.DiscontinuityListener
import com.kadirdogan97.sinifyoklama.network.ModifyListener
import com.kadirdogan97.sinifyoklama.network.LessonDetailRepository

class LessonDetailsViewModel: ViewModel(){
    var modifyListener: ModifyListener?= null
    var detailClickListener: DetailClickListener ?= null
    var discontinuityListener: DiscontinuityListener? = null
    fun fetchDiscontinuityData(ders_id: String?, ogr_id: String?){
        discontinuityListener?.onStarted()
        if(ders_id.isNullOrEmpty()||ogr_id.isNullOrEmpty()){
            discontinuityListener?.onFailure("ders_id veya ogr_id hatali")
            return
        }
        val discontinuityResponse = LessonDetailRepository()
            .getDiscontinuity(ders_id,ogr_id)
        discontinuityListener?.onSuccess(discontinuityResponse)
    }

    fun fetchDiscontinuityTData(ders_id: String?){
        discontinuityListener?.onStarted()
        if(ders_id.isNullOrEmpty()){
            discontinuityListener?.onFailure("ders_id hatali")
            return
        }
        val discontinuityResponse = LessonDetailRepository()
            .getDiscontinuityT(ders_id)
        discontinuityListener?.onSuccess(discontinuityResponse)
    }

    fun setDiscontinuity(ders_id: String?, ogr_id: String?){
        modifyListener?.onStartedModify()
        if(ders_id.isNullOrEmpty()||ogr_id.isNullOrEmpty()){
            modifyListener?.onFailureModify("ders_id veya ogr_id hatali")
            return
        }
        val discontinuityResponse = LessonDetailRepository()
            .setDiscontinuity(ders_id,ogr_id)
        modifyListener?.onSuccessSetDisc(discontinuityResponse)
    }
    fun setBarcode(barkod: String?,ders_id: String?){
        modifyListener?.onStartedModify()
        if(ders_id.isNullOrEmpty()||barkod.isNullOrEmpty()){
            modifyListener?.onFailureModify("ders_id veya barkod hatali")
            return
        }
        val createBarcodeResponse = LessonDetailRepository()
            .setBarcode(barkod,ders_id)
        modifyListener?.onSuccessCreateBarcode(createBarcodeResponse)
    }
    fun closeDiscontinuity(ders_id: String?){
        modifyListener?.onStartedModify()
        if(ders_id.isNullOrEmpty()){
            modifyListener?.onFailureModify("ders_id hatali")
            return
        }
        val discontinuityResponse = LessonDetailRepository()
            .closeDiscontinuity(ders_id)
        modifyListener?.onSuccessCloseDisc(discontinuityResponse)
    }

    fun onCheckDiscontinuityButtonClick(view: View){
        detailClickListener?.onCheckClicked()
    }
    fun onCloseDiscontinuityButtonClick(view: View){
        detailClickListener?.onCloseClicked()
    }
    fun onCreateBarcodeButtonClick(view: View){
        detailClickListener?.onBarcodeClicked()
    }
    fun onSendMailButtonClick(view: View){
        detailClickListener?.onSendMailClicked()
    }
    interface DetailClickListener {
        fun onCheckClicked()
        fun onCloseClicked()
        fun onBarcodeClicked()
        fun onSendMailClicked()
    }



}